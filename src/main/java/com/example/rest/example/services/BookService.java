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
@Path("book")
public class BookService {

    private static final String BOOKSERVICE = "http://localhost:8080/bookservice";
    private static final String COMMENTSERVICE = "http://localhost:8080/bookcomments";

    @Resource(name = "DefaultManagedExecutorService")
    ManagedExecutorService executor;

    Client bookServiceClient;
    WebTarget bookServiceTarget;

    Client commentServiceClient;
    WebTarget commentServiceTarget;

    @PostConstruct
    void initializeRestClients() {

        bookServiceClient = ClientBuilder.newClient();
        bookServiceTarget = bookServiceClient.target(BOOKSERVICE + "/rest/bookinfo");

        commentServiceClient = ClientBuilder.newClient();
        commentServiceTarget = commentServiceClient.target(COMMENTSERVICE + "/rest/comments");

    }

    @GET
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public void bookAndComment(@Suspended final AsyncResponse asyncResponse, @PathParam("isbn") String isbn) {
    //RxJava code shown below
    }
    
    
    public Observable<JsonObject> getBookInfo(final String isbn) {
        return Observable.create((Observable.OnSubscribe<JsonObject>) subscriber -> {

            Runnable r = () -> {
                subscriber.onNext(bookServiceTarget.path(isbn).request().get(JsonObject.class));
                subscriber.onCompleted();
            };

            executor.execute(r);

        });
}
}
