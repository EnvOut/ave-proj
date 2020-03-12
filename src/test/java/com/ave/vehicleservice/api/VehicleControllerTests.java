package com.ave.vehicleservice.api;

import com.ave.vehicleservice.domain.vehicle.VehicleDto;
import com.ave.vehicleservice.domain.vehicle.VehicleDtoFactory;
import com.ave.vehicleservice.exception.AlreadyExists;
import com.ave.vehicleservice.exception.NotFound;
import com.ave.vehicleservice.exception.ValidationException;
import com.ave.vehicleservice.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = VehicleController.class)
@AutoConfigureMockMvc
public class VehicleControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private VehicleService vehicleService;

    @Nested
    @DisplayName("Find by id")
    @SpringBootTest
    public class GetById {

        @Test
        public void whenVehicleExists_thenReturnVehicle() throws Exception {
            var id = UUID.randomUUID().toString();
            VehicleDto vehicle = VehicleDto.builder()
                    .id(id)
                    .build();

            when(vehicleService.getById(anyString())).thenReturn(vehicle);

            var uri = "/api/vehicles/" + id;

            var request = MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        }

        @Test
        public void whenNotExists_thenNotFound() throws Exception {
            var id = UUID.randomUUID().toString();

            when(vehicleService.getById(id)).thenThrow(new NotFound());

            var request = MockMvcRequestBuilders.get("/api/vehicles/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isNotFound());

//            webTestClient.get()
//                    .uri("/api/vehicles/" + id)
//                    .accept(MediaType.APPLICATION_STREAM_JSON)
//                    .exchange()
//                    .expectStatus().isNotFound();
        }

    }

    @Nested
    @DisplayName("Create vehicle")
    @SpringBootTest
    public class CreateVehicle {
        private final String ENDPOINT = "/api/vehicles";

        @Test
        public void whenCreate_thenOk() throws Exception {
            var id = UUID.randomUUID().toString();
            VehicleDto vehicle = VehicleDtoFactory.create(id);

            reset(vehicleService);
            when(vehicleService.create(vehicle)).thenReturn(vehicle);

            var request = MockMvcRequestBuilders.post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(vehicle));
            mockMvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_VALUE));

        }

        @Test
        public void whenCreate_andAlreadyExists_thenConflict() throws Exception {
            var id = UUID.randomUUID().toString();
            VehicleDto vehicle = VehicleDtoFactory.create(id);

            reset(vehicleService);
            when(vehicleService.create(vehicle)).thenThrow(new AlreadyExists());

            var request = MockMvcRequestBuilders.post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(vehicle));
            mockMvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isConflict())
                    .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_VALUE));
        }

        @Test
        public void whenCreate_andNotValid_thenBadRequest() throws Exception {
            var id = UUID.randomUUID().toString();
            VehicleDto vehicle = VehicleDtoFactory.create(id);

            reset(vehicleService);
            when(vehicleService.create(any())).thenThrow(new ValidationException());

            var request = MockMvcRequestBuilders.post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(vehicle));

            mockMvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }
    }
}
