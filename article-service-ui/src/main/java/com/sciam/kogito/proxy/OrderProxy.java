package com.sciam.kogito.proxy;


import com.sciam.kogito.dto.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/order")
@RegisterRestClient(configKey = "order-api")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface OrderProxy {

    @GET
    List<Order> list();
}
