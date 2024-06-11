package com.sciam.kogito.order.service;

import com.sciam.kogito.order.model.Order;
import com.sciam.kogito.order.model.OrderItem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@ApplicationScoped
@Slf4j
public class OrderService {

    @Transactional
    public Order createOrder(Order order) {
        Set<OrderItem> orderItems = order.getOrderItems();
        order.persistAndFlush();
        orderItems.forEach(orderItem -> {
            orderItem.setOrderId(order.getOrderId());
            orderItem.persist();
        });
        return order;
    }
}
