package org.example.config;

import org.example.daos.GuideDAO;
import org.example.daos.TripDAO;
import org.example.dtos.GuideDTO;
import org.example.dtos.TripDTO;
import org.example.entities.Category;

import java.time.LocalDateTime;

public class Populate {

    private final TripDAO tripDAO;
    private final GuideDAO guideDAO;

    public Populate(TripDAO tripDAO, GuideDAO guideDAO) {
        this.tripDAO = tripDAO;
        this.guideDAO = guideDAO;
    }

    public void populate() {
        // Create and save sample guides
        GuideDTO guide1 = new GuideDTO();
        guide1.setFirstName("John");
        guide1.setLastName("Doe");
        guide1.setYearsOfExperience(5);
        guide1 = guideDAO.create(guide1, "john.doe@example.com", "123456789");

        GuideDTO guide2 = new GuideDTO();
        guide2.setFirstName("Jane");
        guide2.setLastName("Smith");
        guide2.setYearsOfExperience(8);
        guide2 = guideDAO.create(guide2, "jane.smith@example.com", "987654321");

        // Create and save sample trips
        TripDTO trip1 = new TripDTO();
        trip1.setStartTime(LocalDateTime.of(2024, 12, 1, 10, 0));
        trip1.setEndTime(LocalDateTime.of(2024, 12, 1, 16, 0));
        trip1.setStartPosition("Beach Area");
        trip1.setName("Beach Adventure");
        trip1.setPrice(150.00);
        trip1.setCategory(Category.BEACH);
        trip1 = tripDAO.create(trip1);

        TripDTO trip2 = new TripDTO();
        trip2.setStartTime(LocalDateTime.of(2024, 12, 5, 9, 0));
        trip2.setEndTime(LocalDateTime.of(2024, 12, 5, 17, 0));
        trip2.setStartPosition("City Center");
        trip2.setName("City Exploration");
        trip2.setPrice(200.00);
        trip2.setCategory(Category.CITY);
        trip2 = tripDAO.create(trip2);

        // Assign guides to trips
        tripDAO.addGuideToTrip(trip1.getId(), guide1.getId());
        tripDAO.addGuideToTrip(trip2.getId(), guide2.getId());
    }
}
