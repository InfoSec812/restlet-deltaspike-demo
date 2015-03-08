package com.zanclus.test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author <a href="">Deven Phillips</a>
 */
@ApplicationScoped
public class Producer {

    EntityManagerFactory emf;

    public Producer() {
        Persistence.createEntityManagerFactory("todo");
    }

    @Produces
    @Default
    @RequestScoped
    public EntityManager create() {
        return this.emf.createEntityManager();
    }
    
    public void dispose(@Disposes @Default EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }
}
