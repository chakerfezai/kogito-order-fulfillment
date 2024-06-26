package com.sciam.kogito.order.resource;


import com.sciam.kogito.order.model.Order;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path = "/order")
public interface OrderResource extends PanacheEntityResource<Order, Long> {
}
