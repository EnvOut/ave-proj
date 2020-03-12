package com.ave.vehicleservice.domain.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class VehicleDto {
    @Pattern(regexp = "[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}",
            message = "Invalid ID format")
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private VehicleLocation location;

    public static VehicleDto from(Vehicle vehicle) {
        VehicleLocation location = VehicleLocation.from(vehicle.getLocation());
        return new VehicleDto(vehicle.getId(), vehicle.getName(), location);
    }

    public Vehicle toVehicle() {
        return new Vehicle(id, name, location.toPoint());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel
    public static class VehicleLocation {
        @JsonProperty
        private double longitude;
        @JsonProperty
        private double latitude;

        public static VehicleLocation from(Point point) {
            return new VehicleLocation(point.getX(), point.getY());
        }

        public Point toPoint() {
            return new Point(longitude, latitude);
        }
    }
}
