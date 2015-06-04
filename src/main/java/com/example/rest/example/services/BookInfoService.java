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

import javax.json.JsonObject;
import javax.json.Json;

import com.sun.jersey.spi.resource.Singleton;

/**
 * A service that provides information on books.
 * 
 * See example at http://www.lordofthejars.com/2014/07/rxjava-java8-java-ee-7-arquillian-bliss.html
 * 
 * @author <a href="mailto:dabecker@paypal.com">Dan Becker</a>
 */
@Singleton
@Path("bookinfo")
public class BookInfoService {

    @GET
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonObject findBookByISBN(@PathParam("isbn") String isbn) {

        return Json.createObjectBuilder()
            .add("author", "George R.R. Martin")
            .add("isbn", "1111")
            .add("title", "A Game Of Thrones").build();
    }

}