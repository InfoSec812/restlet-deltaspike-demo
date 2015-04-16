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

import com.zanclus.restlet.demo.data.ToDoDAO;
import com.zanclus.restlet.demo.data.entities.ToDo;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author <a href="https://github.com/InfoSec812">Deven Phillips</a>
 */
@Path("/v1/todo")
public class ToDoResource implements Serializable {
  
  @Inject
  private ToDoDAO dao;

  @GET
  @Produces({"application/xml", "application/json"})
  public Response getAllToDos() {
    List<ToDo> todos = dao.getAllToDos();
    return Response.ok(todos).build();
  }

  @GET
  @Path("/{id}")
  @Produces({"application/xml", "application/json"})
  public Response getToDoById(@PathParam("id") Long id) {
    ToDo todo = dao.getToDo(id);
    if (todo==null) {
      return Response.noContent().build();
    }
    return Response.ok(todo).build();
  }
  
  @POST
  @Consumes({"application/xml", "application/json"})
  @Produces({"application/xml", "application/json"})
  public Response addToDo(ToDo item) {
    Long id = dao.addToDo(item);
    if (id==null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    return Response.created(URI.create(id.toString())).build();
  }
  
  @PUT
  @Path("/{id}")
  @Produces({"application/xml", "application/json"})
  public Response updateToDo(@PathParam("id") Long id, ToDo item) {
    ToDo todo = dao.updateToDo(item);
    if (todo==null) {
      return Response.notModified().build();
    }
    return Response.accepted(todo).build();
  }
  
  @DELETE
  @Path("/{id}")
  public void deleteToDo(@PathParam("id") Long id) {
    if (dao.deleteToDo(id)) {
      Response.accepted().build();
    } else {
      Response.notModified().build();
    }
  }
}
