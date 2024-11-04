package org.example.controller;

import org.example.config.Populate;
import org.example.daos.GuideDAO;
import org.example.daos.TripDAO;
import org.example.dtos.GuideDTO;
import org.example.dtos.TripDTO;
import org.example.exception.NotFoundException;

import java.util.List;

public class TripController {

    private final TripDAO tripDAO;
    private final GuideDAO guideDAO;

    public TripController(TripDAO tripDAO, GuideDAO guideDAO) {
        this.tripDAO = tripDAO;
        this.guideDAO = guideDAO;
    }

    public List<TripDTO> getAllTrips() {
        return tripDAO.getAll();
    }

    public TripDTO getTripById(Integer id) {
        TripDTO trip = tripDAO.getById(id);
        if (trip == null) {
            throw new NotFoundException("Trip with ID " + id + " not found.");
        }
        return trip;
    }

    public TripDTO createTrip(TripDTO tripDTO) {
        return tripDAO.create(tripDTO);
    }

    public TripDTO updateTrip(Integer id, TripDTO tripDTO) {
        tripDTO.setId(id);
        return tripDAO.update(tripDTO);
    }

    public void deleteTrip(Integer id) {
        TripDTO trip = tripDAO.getById(id);
        if (trip == null) {
            throw new NotFoundException("Trip with ID " + id + " not found.");
        }
        tripDAO.delete(id);
    }

    public void addGuideToTrip(Integer tripId, Integer guideId) {
        GuideDTO guideDTO = guideDAO.getById(guideId);
        if (guideDTO != null) {
            tripDAO.addGuideToTrip(tripId, guideId);
        } else {
            throw new IllegalArgumentException("Guide with ID " + guideId + " does not exist.");
        }
    }

    public void populateDatabase() {
        new Populate(tripDAO, guideDAO).populate();
    }
}



