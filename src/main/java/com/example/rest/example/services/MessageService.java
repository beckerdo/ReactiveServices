package com.example.rest.example.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.rest.example.model.Message;

import org.unix4j.Unix4j;

/** 
 * This is an example service that serves up the entities.
 * @author <a href="mailto:dabecker@paypal.com">Dan Becker</a>
 */
@Component
@Scope("request")
@Path("/messages/")
public class MessageService {

	/** A static in-memory repository. Some example data are provided. */
    protected static List<Message> messages = new ArrayList<Message>(
    	Arrays.asList(
        		new Message(), new Message(), new Message(), new Message(), new Message(),
        		new Message(), new Message(), new Message(), new Message(), new Message()
    	));

    /** 
     * Get/retrieve a list of all entities from the store.
     * Use Accept request header to specify format.
     * @return
     */
    @GET
    @Produces({"application/json"})
    public List<Message> getMessages() {
    	return messages;
    }

    /**
     * Get/retrieve a single item from the store.
     * Use Accept request header to specify format.
     * @param index the index of the item in the store
     * @return
     */
    @GET
    @Produces({"application/json"})
    @Path("/{index}")
    public Response getMessage(@PathParam("index") int index) {
        try {
            return Response.status(Status.OK).entity(messages.get(index)).build();
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Status.NOT_FOUND).entity("Message " + index + " not found").build();
        }
    }

    /**
     * Post/create a new entity in th the store.
     * Use Content-Type request header to specify body format.
     * @param messageDTO
     * @return
     */
    @POST
    @Consumes({"application/json"})
    public Response addMessage(Message message) {
        messages.add(message);
        return Response.status(Status.OK).build();
    }

    /**
     * Put/update an existing entity in the store.
     * Use Content-Type request header to specify body format.

     * @param messageDTO
     * @return
     */
    @PUT
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("/{index}")
    public Response updateMessage(@PathParam("index") int index, Message message) {
        try {
        	Message previous = messages.get( index );
        	// String update = Unix4j.fromString( content ).sed( sedCommand ).toStringResult();
        	// System.out.println( "Content=\"" + content + "\", update=\"" + update + "\"");
            messages.set(index, message );
            return Response.status(Status.OK).entity(previous).build();        		
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Status.NOT_FOUND).entity( "Message " + index + " not found").build();
        }
    }

    /**
     * Delete/remove a single item from the store.
     * The entity deleted is returned in the response body.
     * Use Accept request header to specify format.
     * @param index the index of the item in the store
     * @return
     */
    @DELETE
    @Produces({"application/json"})
    @Path("/{index}")
    public Response removeMessage(@PathParam("index") int index) {
        try {
            Message message= messages.get(index);
            messages.remove(index);
            return Response.status(Status.OK).entity(message).build();
        } catch (IndexOutOfBoundsException e) {
            return Response.status(Status.NOT_FOUND).entity( "Message " + index + " not found").build();
        }
    }
}