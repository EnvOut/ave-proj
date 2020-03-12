package com.ave.vehicleservice.domain.vehicle;

import com.ave.vehicleservice.util.RegexpUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class VehicleDto {
    @Pattern(regexp = RegexpUtil.UUID_REGEXP, message = "Invalid ID format")
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    @Valid
    @NotNull
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
        @Positive
        @Max(value = 180)
        @NotNull
        private Double longitude;

        @JsonProperty
        @Positive
        @Max(value = 90)
        @NotNull
        private Double latitude;

        public static VehicleLocation from(Point point) {
            return new VehicleLocation(point.getX(), point.getY());
        }

        public Point toPoint() {
            return new Point(longitude, latitude);
        }
    }
}
