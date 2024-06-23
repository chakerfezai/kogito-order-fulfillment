package com.sciam.kogito.resource;

import com.sciam.kogito.proxy.StockProxy;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/product")
public class ProductResource  {

    @Inject
    @RestClient
    StockProxy stockProxy;

    @GET
    public Response allProduct() {
        try {
            return Response.ok(stockProxy.allProduct()).build();
        }catch (Exception ex) {
            return Response.serverError().entity(ex.getMessage()).build();
        }

    }
}
