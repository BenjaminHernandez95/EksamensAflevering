package org.example.daos;

import org.example.dtos.TripDTO;

import java.util.Set;

public interface ITripGuideDAO {
    void addGuideToTrip(int tripId, int guideId);
    Set<TripDTO> getTripsByGuide(int guideId);
}