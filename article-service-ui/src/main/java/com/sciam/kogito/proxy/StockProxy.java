package com.sciam.kogito.proxy;



import com.sciam.kogito.dto.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/api/product")
@RegisterRestClient(configKey = "stock-api")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface StockProxy {

    @GET
    List<Product> allProduct();
}
