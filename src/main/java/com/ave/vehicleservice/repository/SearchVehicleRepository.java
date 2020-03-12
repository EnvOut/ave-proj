package com.ave.vehicleservice.repository;

import com.ave.vehicleservice.domain.vehicle.Vehicle;
import org.springframework.data.geo.Point;

import java.util.List;

public interface SearchVehicleRepository {
    List<Vehicle> findByBox(Point firstPoint, Point secondPoint);
}
