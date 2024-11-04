package org.example.daos;

import jakarta.persistence.EntityManager;
import org.example.dtos.GuideDTO;
import org.example.entities.Guide;

public class GuideDAO {

    private EntityManager em;

    public GuideDAO(EntityManager em) {
        this.em = em;
    }

    public GuideDTO create(GuideDTO guideDTO, String email, String phone) {
        Guide guide = new Guide();
        guide.setFirstName(guideDTO.getFirstName());
        guide.setLastName(guideDTO.getLastName());
        guide.setYearsOfExperience(guideDTO.getYearsOfExperience());
        guide.setEmail(email);  // Sensitive information
        guide.setPhone(phone);  // Sensitive information

        em.getTransaction().begin();
        em.persist(guide);
        em.getTransaction().commit();

        guideDTO.setId(guide.getId());
        return guideDTO;
    }

    public GuideDTO getById(Integer id) {
        Guide guide = em.find(Guide.class, id);
        if (guide == null) return null;

        GuideDTO guideDTO = new GuideDTO();
        guideDTO.setId(guide.getId());
        guideDTO.setFirstName(guide.getFirstName());
        guideDTO.setLastName(guide.getLastName());
        guideDTO.setYearsOfExperience(guide.getYearsOfExperience());

        return guideDTO;
    }
}

