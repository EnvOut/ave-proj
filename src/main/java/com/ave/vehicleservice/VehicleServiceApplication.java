package com.ave.vehicleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class VehicleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleServiceApplication.class, args);
    }

//    @Bean
//    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory){
//        return new MongoTemplate(mongoDatabaseFactory);
//    }
}
