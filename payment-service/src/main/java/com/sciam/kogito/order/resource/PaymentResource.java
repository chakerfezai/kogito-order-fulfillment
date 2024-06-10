package com.sciam.kogito.order.resource;


import com.sciam.kogito.order.entity.Payment;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path = "/payment")
public interface PaymentResource extends PanacheEntityResource<Payment, Long> {
}
