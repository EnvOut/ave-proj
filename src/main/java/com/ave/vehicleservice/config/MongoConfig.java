package com.ave.vehicleservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.ave.vehicleservice.repository")
@EnableMongoAuditing
public class MongoConfig {
}
