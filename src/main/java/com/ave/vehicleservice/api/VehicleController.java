package com.ave.vehicleservice.api;

import com.ave.vehicleservice.domain.api.ExceptionResp;
import com.ave.vehicleservice.domain.vehicle.VehicleDto;
import com.ave.vehicleservice.domain.vehicle.VehicleSearchRequest;
import com.ave.vehicleservice.service.VehicleService;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/vehicles")
@RestController
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/search")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = VehicleDto.class, responseContainer = "List")})
    public List<VehicleDto> searchVehicles(@RequestBody @ApiParam VehicleSearchRequest searchRequest) {
        return vehicleService.findByBox(searchRequest.getFirstPoint().toPoint(), searchRequest.getSecondPoint().toPoint());
    }

    @Validated
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = VehicleDto.class),
            @ApiResponse(code = 404, message = "Not found", response = ExceptionResp.class),
            @ApiResponse(code = 400, message = "Data is not not valid", response = ExceptionResp.class)})
    public VehicleDto find(@PathVariable @ApiParam String id) {
        return vehicleService.getById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = VehicleDto.class)})
    @Validated
    public VehicleDto create(@Valid @ApiParam @RequestBody VehicleDto vehicleDto) {
        return vehicleService.create(vehicleDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = VehicleDto.class, responseContainer = "List")})
    public List<VehicleDto> findAll() {
        return vehicleService.findAll();
    }

    @PatchMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = VehicleDto.class)})
    public VehicleDto update(@PathVariable @ApiParam String id, @RequestBody @ApiParam VehicleDto vehicle) {
        vehicle.setId(id);
        return vehicleService.update(vehicle);
    }
}
