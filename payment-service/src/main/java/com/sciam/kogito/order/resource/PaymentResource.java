package com.sciam.kogito.order.resource;


import com.sciam.kogito.order.entity.Payment;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

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
}
