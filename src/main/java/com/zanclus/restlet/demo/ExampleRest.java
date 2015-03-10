package com.zanclus.restlet.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/example")
@Produces({"text/plain"})
public class ExampleRest {

    @GET
    public String getServerTime() {
        return System.currentTimeMillis()+"";
    }
}
