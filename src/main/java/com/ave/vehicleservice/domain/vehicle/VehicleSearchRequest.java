package com.ave.vehicleservice.domain.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class VehicleSearchRequest {
    @JsonProperty
    private VehicleDto.VehicleLocation firstPoint;
    @JsonProperty
    private VehicleDto.VehicleLocation secondPoint;
}
