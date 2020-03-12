package com.ave.vehicleservice.repository;

import com.ave.vehicleservice.domain.vehicle.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String>, SearchVehicleRepository {
}
