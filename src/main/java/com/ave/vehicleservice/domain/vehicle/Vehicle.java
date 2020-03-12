package com.ave.vehicleservice.domain.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import static org.springframework.data.mongodb.core.index.GeoSpatialIndexType.GEO_2D;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Vehicle {
    @Id
    private String id;

    @Field
    private String name;

    @Field
    @GeoSpatialIndexed(name = "vehicles.location.2Dindex", type = GEO_2D)
    private Point location;

    public static Vehicle newRecord(String name, Point location) {
        return Vehicle.builder().name(name).location(location).build();
    }
}
