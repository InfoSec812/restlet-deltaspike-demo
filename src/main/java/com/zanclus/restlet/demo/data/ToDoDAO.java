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
 * @author <a href="">Deven Phillips</a>
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
