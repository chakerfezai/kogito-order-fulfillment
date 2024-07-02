package com.sciam.kogito.resource;


import com.sciam.kogito.dto.Order;
import com.sciam.kogito.service.KafkaOrdersProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;


import java.util.UUID;

@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@ApplicationScoped
public class OrderResource {




    @Inject
    KafkaOrdersProducer kafkaOrdersProducer;

    @POST
    public Response save(Order order) {
        if (order == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        UUID transactionId = UUID.randomUUID();
        order.setTransactionId(transactionId);
        log.info("received order {}", order);
        kafkaOrdersProducer.publishOrder(order);
        return Response.ok(transactionId).build();
    }
}
