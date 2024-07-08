package com.sciam.kogito.resource;

import com.sciam.kogito.dto.Payment;
import com.sciam.kogito.proxy.PaymentProxy;
import com.sciam.kogito.service.KafkaOrdersProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@Path("/payment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@ApplicationScoped
public class PaymentResource {

    @Inject
    @RestClient
    PaymentProxy paymentProxy;

    @Inject
    KafkaOrdersProducer kafkaOrdersProducer;


    @GET
    @Path("/byTransactionId/{transactionId}/order")
    public Response getPayment(@PathParam("transactionId") String transactionId) {
        log.info("Getting payment for transaction id {}", transactionId);
        return paymentProxy.getPaymentByTransactionId(transactionId);
    }

    @POST
    @Path("/validate")
    public Response validatePayment(Payment payment) {
        kafkaOrdersProducer.publishPayment(payment);
        return Response.ok().build();
    }
}
