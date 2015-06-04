package com.example.rest.example.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("comments")
public class CommentsService {

    @GET
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray bookComments(@PathParam("isbn") String isbn) {

        return Json.createArrayBuilder().add("Good Book").add("Awesome").build();

    }

}
