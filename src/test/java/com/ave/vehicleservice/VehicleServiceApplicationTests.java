package com.ave.vehicleservice;

import com.ave.vehicleservice.api.VehicleControllerTests;
import com.ave.vehicleservice.repository.VehicleRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//        classes = {VehicleControllerTests.class})
//@EnableAutoConfiguration
class VehicleServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
