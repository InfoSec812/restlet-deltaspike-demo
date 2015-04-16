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
package com.zanclus.restlet.demo.data;

import com.zanclus.restlet.demo.data.entities.ToDo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@SessionScoped
@Slf4j
public class ToDoDAO implements Serializable {

    @Inject
    private EntityManager em;

    public ToDoDAO() {
    }

    @Transactional
    public List<ToDo> getAllToDos() {
        try {
            return em.createQuery("FROM ToDo").getResultList();
        } catch (Exception e) {
            LOG.error("Error getting ToDo list", e);
            return null;
        }
    }

    @Transactional
    public ToDo getToDo(Long id) {
        try {
            return em.find(ToDo.class, id);
        } catch (Exception e) {
            LOG.error("Error getting ToDo list", e);
            return null;
        }
    }

    @Transactional
    public Long addToDo(ToDo item) {
        try {
            ToDo retVal = em.merge(item);
            em.flush();
            return retVal.id();
        } catch (Exception e) {
            LOG.error("Error getting ToDo list", e);
            return null;
        }
    }

    @Transactional
    public ToDo updateToDo(ToDo item) {
        try {
            ToDo retVal = em.merge(item);
            em.flush();
            return retVal;
        } catch (Exception e) {
            LOG.error("Error getting ToDo list", e);
            return null;
        }
    }

    @Transactional
    public boolean deleteToDo(Long id) {
        try {
            em.remove(em.find(ToDo.class, id));
        } catch (Exception e) {
            LOG.error("Error getting ToDo list", e);
            return false;
        }
        return true;
    }
}
