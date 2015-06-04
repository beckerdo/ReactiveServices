package com.example.rest.example.clients;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

/**
 * Async request/response client
 * 
 * Based on https://hc.apache.org/httpcomponents-asyncclient-dev/httpasyncclient/examples/org/apache/http/examples/nio/client/AsyncClientHttpExchange.java
 * 
 *
 * @author <a href="mailto:dan@danbecker.info">Dan Becker</a>
 *
 */
public class HttpClientAsync {
	
    public static void main(final String[] args) throws Exception {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            httpclient.start();
            HttpGet request = new HttpGet("http://www.apache.org/");
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();
            System.out.println("Response: " + response.getStatusLine());
            System.out.println("Shutting down");
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }

}
