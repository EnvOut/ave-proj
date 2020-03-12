package com.ave.vehicleservice.repository;

import com.ave.vehicleservice.domain.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class VehicleRepositoryTest {
    @Autowired
    private VehicleRepository vehicleRepository;

    @DisplayName("given object to save"
            + " when save object using repository"
            + " then object is saved")
    @Test
    public void testNewRecord() {
        Vehicle testObject = Vehicle.newRecord("name", new Point(1, 1));
        Vehicle document = vehicleRepository.save(testObject);

        assertThat(document)
                .isNotNull()
                .hasFieldOrProperty("id")
                .extracting("name", "location")
                .containsExactly(testObject.getName(), testObject.getLocation());
    }

    @Nested
    class Search {
        @BeforeEach
        public void initDataset() {
            vehicleRepository.deleteAll();

            Vehicle kh_city1 = Vehicle.newRecord("kh_city1", new Point(50.007238, 36.237697));
            Vehicle kh_city2 = Vehicle.newRecord("kh_city2", new Point(50.006080, 36.241602));
            Vehicle kh_city3 = Vehicle.newRecord("kh_city3", new Point(50.003708, 36.235851));
            Vehicle pisochin = Vehicle.newRecord("pisochin1", new Point(49.962412, 36.130407));
            Vehicle bezludovka = Vehicle.newRecord("bezludovka", new Point(49.869557, 36.302283));
            Vehicle rogan = Vehicle.newRecord("rogan", new Point(49.936542, 36.487007));
            Vehicle derhachi = Vehicle.newRecord("dergachi", new Point(50.102624, 36.127049));

            List<Vehicle> vehicles = List.of(kh_city1, kh_city2, kh_city3, pisochin, bezludovka, rogan, derhachi);
            vehicleRepository.saveAll(vehicles);
        }

        @Test
        @DisplayName("findByBox" +
                "Should return documents that are within the bounds of the rectangle")
        public void findByBox() {

            Point firstPoint = new Point(50.016123, 36.204586);
            Point secondPoint = new Point(49.958572, 36.288557);
            List<Vehicle> vehicleList = vehicleRepository.findWithBox(firstPoint, secondPoint)
                    .stream()
                    .sorted(Comparator.comparing(Vehicle::getName))
                    .collect(Collectors.toList());

            assertThat(vehicleList)
                    .extracting("name")
                    .containsOnly("kh_city1", "kh_city2", "kh_city3");
        }
    }
}