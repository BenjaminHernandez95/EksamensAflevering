package org.example.daos;

import jakarta.persistence.EntityManager;
import org.example.dtos.GuideDTO;
import org.example.dtos.TripDTO;
import org.example.entities.Guide;
import org.example.entities.Trip;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TripDAO implements ITripGuideDAO,IDAO<TripDTO> {

    private EntityManager em;

    public TripDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public TripDTO create(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setStartTime(tripDTO.getStartTime());
        trip.setEndTime(tripDTO.getEndTime());
        trip.setStartPosition(tripDTO.getStartPosition());
        trip.setName(tripDTO.getName());
        trip.setPrice(tripDTO.getPrice());
        trip.setCategory(tripDTO.getCategory());

        em.getTransaction().begin();
        em.persist(trip);
        em.getTransaction().commit();

        tripDTO.setId(trip.getId()); // Set the generated ID in DTO
        return tripDTO;
    }

    @Override
    public List<TripDTO> getAll() {
        List<Trip> trips = em.createQuery("SELECT t FROM Trip t", Trip.class).getResultList();
        return trips.stream().map(trip -> {
            TripDTO dto = new TripDTO();
            dto.setId(trip.getId());
            dto.setStartTime(trip.getStartTime());
            dto.setEndTime(trip.getEndTime());
            dto.setStartPosition(trip.getStartPosition());
            dto.setName(trip.getName());
            dto.setPrice(trip.getPrice());
            dto.setCategory(trip.getCategory());
            return dto;
        }).collect(Collectors.toList());    }

    @Override
    public TripDTO getById(Integer id) {
        Trip trip = em.find(Trip.class, id);
        if (trip == null) return null;

        TripDTO dto = new TripDTO();
        dto.setId(trip.getId());
        dto.setStartTime(trip.getStartTime());
        dto.setEndTime(trip.getEndTime());
        dto.setStartPosition(trip.getStartPosition());
        dto.setName(trip.getName());
        dto.setPrice(trip.getPrice());
        dto.setCategory(trip.getCategory());

        if (trip.getGuide() != null) {
            Guide guide = trip.getGuide();
            GuideDTO guideDTO = new GuideDTO();
            guideDTO.setId(guide.getId());
            guideDTO.setFirstName(guide.getFirstName());
            guideDTO.setLastName(guide.getLastName());
            guideDTO.setYearsOfExperience(guide.getYearsOfExperience());
            dto.setGuide(guideDTO);
        }

        return dto;
    }

    @Override
    public TripDTO update(TripDTO tripDTO) {
        Trip trip = em.find(Trip.class, tripDTO.getId());
        if (trip == null) return null;

        em.getTransaction().begin();
        trip.setStartTime(tripDTO.getStartTime());
        trip.setEndTime(tripDTO.getEndTime());
        trip.setStartPosition(tripDTO.getStartPosition());
        trip.setName(tripDTO.getName());
        trip.setPrice(tripDTO.getPrice());
        trip.setCategory(tripDTO.getCategory());
        em.getTransaction().commit();

        return tripDTO;    }

    @Override
    public void delete(Integer id) {
        Trip trip = em.find(Trip.class, id);
        if (trip != null) {
            em.getTransaction().begin();
            em.remove(trip);
            em.getTransaction().commit();
        }
    }

    public void addGuideToTrip(int tripId, int guideId) {
        Trip trip = em.find(Trip.class, tripId);
        Guide guide = em.find(Guide.class, guideId);

        if (trip != null && guide != null) {
            em.getTransaction().begin();
            trip.setGuide(guide);
            em.merge(trip);
            em.getTransaction().commit();
        }
    }

    public Set<TripDTO> getTripsByGuide(int guideId) {
        Guide guide = em.find(Guide.class, guideId);
        if (guide == null) return Collections.emptySet();

        return guide.getTrips().stream().map(trip -> {
            TripDTO dto = new TripDTO();
            dto.setId(trip.getId());
            dto.setStartTime(trip.getStartTime());
            dto.setEndTime(trip.getEndTime());
            dto.setStartPosition(trip.getStartPosition());
            dto.setName(trip.getName());
            dto.setPrice(trip.getPrice());
            dto.setCategory(trip.getCategory());
            return dto;
        }).collect(Collectors.toSet());
    }
}
