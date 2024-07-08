package com.sciam.kogito.proxy;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/payment")
@RegisterRestClient(configKey = "payment-api")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface PaymentProxy {

    @GET
    @Path("/byTransactionId/{transactionId}/order")
    Response getPaymentByTransactionId(@PathParam("transactionId") String transactionId);
}
