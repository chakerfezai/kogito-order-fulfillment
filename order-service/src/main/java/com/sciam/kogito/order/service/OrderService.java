package com.sciam.kogito.order.service;

import com.sciam.kogito.order.model.Order;
import com.sciam.kogito.order.model.OrderItem;
import com.sciam.kogito.order.model.OrderStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Slf4j
public class OrderService {

    @Transactional
    public Order placeOrder(Order order, KogitoProcessContext context) {
        List<OrderItem> orderItems = order.getOrderItems();
        order.setProcessInstanceId(context.getProcessInstance().getStringId());
        order.persistAndFlush();
        orderItems.forEach(orderItem -> {
            orderItem.setOrderId(order.getOrderId());
            orderItem.persist();
        });
        return order;
    }

    public Order cancelOrder(Order order) {
        log.info("Cancelling order {}", order.getOrderId());
        return updateOrder(order.getOrderId(), OrderStatus.CANCELLED);
    }

    public Order completeOrder(Order order) {
        log.info("Completing order {}", order.getOrderId());
        return updateOrder(order.getOrderId(), OrderStatus.DELIVERED);
    }


    private Order updateOrder(long orderId, OrderStatus status) {
        Optional<Order> order = Order.findByIdOptional(orderId);
        if (order.isPresent()) {
            Order order1 = order.get();
            order1.setStatus(status);
            order1.persistAndFlush();
            return order1;
        }
        return null;
    }


}
