/**
 * Copyright (C) 2015 Deven Phillips (dphillips@zanclus.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
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
