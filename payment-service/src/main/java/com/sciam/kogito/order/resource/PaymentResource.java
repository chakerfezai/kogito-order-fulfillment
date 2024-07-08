package com.sciam.kogito.order.resource;


import com.sciam.kogito.order.entity.Payment;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


import java.util.UUID;

@ResourceProperties(path = "/payment")
public interface PaymentResource extends PanacheEntityResource<Payment, Long> {

    @POST
    @Path("/create")
    @Produces("application/json")
    @Consumes("application/json")
    @Transactional
    default Response create(Payment payment) {
        payment.persist();
        return Response.accepted().entity(payment.id).build();
    }

    @GET
    @Path("/byTransactionId/{transactionId}/order")
    @Produces("application/json")
    @Consumes("application/json")
    @Transactional
    default Response getPaymentByTransactionId(@PathParam("transactionId") String transactionId) {
        Payment payment = Payment.findByTransactionId(UUID.fromString(transactionId));
        return Response.accepted().entity(payment).build();
    }

}
