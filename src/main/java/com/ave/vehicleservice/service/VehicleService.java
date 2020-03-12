package com.ave.vehicleservice.service;

import com.ave.vehicleservice.domain.vehicle.Vehicle;
import com.ave.vehicleservice.domain.vehicle.VehicleDto;
import com.ave.vehicleservice.exception.NotFound;
import com.ave.vehicleservice.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleDto getById(String id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(NotFound::new);
        return toDto(vehicle);
    }

    public VehicleDto create(VehicleDto dto) {
        Vehicle vehicle = vehicleRepository.save(dto.toVehicle());
        return toDto(vehicle);
    }

    public List<VehicleDto> findAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return toDto(vehicles);
    }

    public List<VehicleDto> findByBox(Point firstPoint, Point secondPoint) {
        List<Vehicle> results = vehicleRepository.findWithBox(firstPoint, secondPoint);
        return toDto(results);
    }

    public List<VehicleDto> toDto(List<Vehicle> vehicles) {
        return vehicles.stream()
                .map(VehicleDto::from)
                .collect(Collectors.toList());
    }

    public VehicleDto toDto(Vehicle vehicle) {
        return VehicleDto.from(vehicle);
    }

    public VehicleDto update(VehicleDto vehicleDto){
        Vehicle document = vehicleRepository.findById(vehicleDto.getId())
                .orElseThrow(NotFound::new);

        boolean isNameUpdated = vehicleDto.getName() != null;
        boolean isLocationUpdated = vehicleDto.getLocation() != null;

        if (isNameUpdated || isLocationUpdated) {
            if (isNameUpdated){
                document.setName(vehicleDto.getName());
            }

            if (isLocationUpdated){
                Point location = vehicleDto.getLocation().toPoint();
                document.setLocation(location);
            }

            vehicleRepository.save(document);
        }
        return toDto(document);
    }
}
