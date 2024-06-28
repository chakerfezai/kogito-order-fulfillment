package com.sciam.kogito.order.resource;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello")
public class HelloResource {

    @GET
    public String HelloResource() {
        return "Hello";
    }
}
