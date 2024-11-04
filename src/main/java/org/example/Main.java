package org.example;

import io.javalin.Javalin;
import jakarta.persistence.EntityManager;
import org.example.config.HibernateConfig;
import org.example.config.Populate;
import org.example.controller.TripController;
import org.example.daos.GuideDAO;
import org.example.daos.TripDAO;
import org.example.routes.TripRoutes;

public class Main {
    public static void main(String[] args) {
        EntityManager em = HibernateConfig.getEntityManagerFactory("trip").createEntityManager();
        TripDAO tripDAO = new TripDAO(em);
        GuideDAO guideDAO = new GuideDAO(em);
        TripController tripController = new TripController(tripDAO, guideDAO);

        Javalin app = Javalin.create().start(8080);
        new TripRoutes(tripController).registerRoutes(app);
    }
}