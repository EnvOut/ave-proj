package com.ave.vehicleservice.domain.vehicle;

public class VehicleDtoFactory {
    public static VehicleDto create(String id) {
        return VehicleDto.builder().id(id).build();
    }
}
