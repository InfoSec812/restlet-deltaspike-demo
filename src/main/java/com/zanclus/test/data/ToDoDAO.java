package com.zanclus.test.data;

import com.zanclus.test.data.entities.ToDo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

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
    public ToDo addToDo(ToDo item) {
        try {
            em.persist(item);
            return item;
        } catch (Exception e) {
            LOG.error("Error getting ToDo list", e);
            return null;
        }
    }

    @Transactional
    public ToDo updateToDo(ToDo item) {
        try {
            em.merge(item);
            return em.find(ToDo.class, item.id());
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
