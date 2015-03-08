/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanclus.test.data;

import static org.junit.Assert.*;

import com.zanclus.test.data.entities.ToDo;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author dphillips
 */
@RunWith(CdiTestRunner.class)
public class ToDoDAOTest {
    
    @Inject
    ToDoDAO dao;
    
    ToDo test1,test2;

    @Before
    public void setUp() {
        test1 = ToDo.builder().title("TEST 1").description("TEST 1 Description").due(new Date(2030, 1, 1, 9, 0)).build();
        test2 = ToDo.builder().title("TEST 2").description("TEST 2 Description").due(new Date(2040, 2, 2, 14, 0)).build();
    }

    /**
     * Test of getAllToDos method, of class ToDoDAO.
     */
    @Test
    public void testGetAllToDos() {
        List<ToDo> todos = dao.getAllToDos();
        assertNotNull("The returned value MUST NOT be null.", todos);
        assertTrue("The returned list MUST be of length 0", todos.isEmpty());
    }

    /**
     * Test of getToDo method, of class ToDoDAO.
     */
    @Test
    public void testGetToDo() {
        ToDo persisted1 = dao.addToDo(test1);
        ToDo item = dao.getToDo(1L);
        assertNotNull("The returned ToDo item MUST NOT be null", item);
        assertTrue("The returned ToDo item MUST have an ID of '1'", item.id()==1L);
    }

    /**
     * Test of addToDo method, of class ToDoDAO.
     */
    @Test
    public void testAddToDo() {
        ToDo persisted1 = dao.addToDo(test1);
        assertNotNull("The returned entity MUST NOT be null", persisted1);
        assertNotNull("Once persisted to the database, the ID MUST NOT be null.", persisted1.id());
        ToDo persisted2 = dao.addToDo(test2);
        assertNotNull("The returned entity MUST NOT be null", persisted2);
        assertNotNull("Once persisted to the database, the ID MUST NOT be null.", persisted2.id());
        assertNotEquals("The IDs of the entities MUST NOT be the same.", persisted1.id(), persisted2.id());
    }

    /**
     * Test of updateToDo method, of class ToDoDAO.
     */
    @Test
    public void testUpdateToDo() {
        ToDo persisted1 = dao.addToDo(test1);
        ToDo item = dao.getToDo(1L);
        Date origDue = item.due();
        item.due(new Date());
        ToDo updated = dao.updateToDo(item);
        assertNotNull("The returned entity MUST NOT be null", updated);
        assertNotEquals("The updated entity MUST NOT have the same due date and the original.", origDue, updated.due());
    }

    /**
     * Test of deleteToDo method, of class ToDoDAO.
     */
    @Test
    public void testDeleteToDo() {
        ToDo persisted1 = dao.addToDo(test1);
        boolean retVal = dao.deleteToDo(1L);
        assertTrue("Delete operation MUST return true for object which exists in DB.", retVal);
    }
    
}
