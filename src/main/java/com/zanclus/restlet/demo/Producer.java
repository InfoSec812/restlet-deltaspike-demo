package com.zanclus.restlet.demo;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

/**
 *
 * @author <a href="">Deven Phillips</a>
 */
@ApplicationScoped
public class Producer {

    EntityManagerFactory emf;

    public Producer() {
        createPU();
    }

    @Produces
    @Default
    @RequestScoped
    public EntityManager create() {
        createPU();
        return this.emf.createEntityManager();
    }

    @Produces
    @Default
    @RequestScoped
    public PersistenceUnitUtil getUtil() {
        createPU();
        return emf.getPersistenceUnitUtil();
    }

    private void createPU() {
        if (emf==null) {
            emf = Persistence.createEntityManagerFactory("todo");
        }
    }
    
    public void dispose(@Disposes @Default EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }
}
