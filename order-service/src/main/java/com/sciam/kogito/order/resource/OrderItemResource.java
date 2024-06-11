package com.sciam.kogito.order.resource;


import com.sciam.kogito.order.model.OrderItem;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path = "/orderItem")
public interface OrderItemResource extends PanacheEntityResource<OrderItem, Long> {
}
