package org.example.routes;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.example.controller.TripController;
import org.example.dtos.TripDTO;
import org.example.exception.ErrorResponse;
import org.example.exception.NotFoundException;

public class TripRoutes {

    private final TripController tripController;

    public TripRoutes(TripController tripController) {
        this.tripController = tripController;
    }

    public void registerRoutes(Javalin app) {
        app.get("/trips", this::getAllTrips);
        app.get("/trips/{id}", this::getTripById);
        app.post("/trips", this::createTrip);
        app.put("/trips/{id}", this::updateTrip);
        app.delete("/trips/{id}", this::deleteTrip);
        app.put("/trips/{tripId}/guides/{guideId}", this::addGuideToTrip);
        app.post("/trips/populate", ctx -> tripController.populateDatabase());
        // Exception handling
        app.exception(NotFoundException.class, this::handleNotFoundException);

    }


    private void getAllTrips(Context ctx) {
        ctx.json(tripController.getAllTrips());
    }

    private void getTripById(Context ctx) {
        Integer id = Integer.parseInt(ctx.pathParam("id"));
        TripDTO trip = tripController.getTripById(id);
        if (trip != null) {
            ctx.json(trip);
        } else {
            ctx.status(404).result("Trip not found");
        }
    }

    private void createTrip(Context ctx) {
        TripDTO tripDTO = ctx.bodyAsClass(TripDTO.class);
        TripDTO createdTrip = tripController.createTrip(tripDTO);
        ctx.status(201).json(createdTrip);
    }

    private void updateTrip(Context ctx) {
        Integer id = Integer.parseInt(ctx.pathParam("id"));
        TripDTO tripDTO = ctx.bodyAsClass(TripDTO.class);
        TripDTO updatedTrip = tripController.updateTrip(id, tripDTO);
        if (updatedTrip != null) {
            ctx.json(updatedTrip);
        } else {
            throw new NotFoundException("Trip with ID " + id + " not found.");
        }
    }

    private void deleteTrip(Context ctx) {
        Integer id = Integer.parseInt(ctx.pathParam("id"));
        tripController.deleteTrip(id);
        ctx.status(204); // No content
    }

    private void addGuideToTrip(Context ctx) {
        try {
            Integer tripId = Integer.parseInt(ctx.pathParam("tripId"));
            Integer guideId = Integer.parseInt(ctx.pathParam("guideId"));
            tripController.addGuideToTrip(tripId, guideId);
            ctx.status(204);
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        }
    }

    private void handleNotFoundException(NotFoundException e, Context ctx) {
        ctx.status(404).json(new ErrorResponse(404, e.getMessage()));
    }

}



