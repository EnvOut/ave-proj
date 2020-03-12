package com.ave.vehicleservice.repository;

import com.ave.vehicleservice.domain.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchVehicleRepositoryImpl implements SearchVehicleRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Vehicle> findByBox(Point firstPoint, Point secondPoint) {
        Box box = new Box(firstPoint, secondPoint);
        Criteria criteria = new Criteria("location").within(box);

        Query query = new Query().addCriteria(criteria);
        return mongoTemplate.find(query, Vehicle.class);
    }
}
