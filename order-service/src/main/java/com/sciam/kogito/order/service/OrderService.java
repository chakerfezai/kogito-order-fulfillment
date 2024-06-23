package com.sciam.kogito.order.service;

import com.sciam.kogito.order.model.Order;
import com.sciam.kogito.order.model.OrderItem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

import java.util.List;

@ApplicationScoped
@Slf4j
public class OrderService {

    @Transactional
    public Order createOrder(Order order, KogitoProcessContext context) {
        List<OrderItem> orderItems = order.getOrderItems();
        order.setProcessInstanceId(context.getProcessInstance().getStringId());
        order.persistAndFlush();
        orderItems.forEach(orderItem -> {
            orderItem.setOrderId(order.getOrderId());
            orderItem.persist();
        });
        return order;
    }


}
