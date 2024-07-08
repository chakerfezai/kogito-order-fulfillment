package com.sciam.kogito.resource;


import com.sciam.kogito.dto.Shipping;
import com.sciam.kogito.service.KafkaOrdersProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/shipping")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@ApplicationScoped
public class ShippingResource {


    @Inject
    KafkaOrdersProducer kafkaOrdersProducer;

    @POST
    @Path("/action")
    public Response validatePayment(Shipping shipping) {
        kafkaOrdersProducer.publishShipping(shipping);
        return Response.ok().build();
    }
}
