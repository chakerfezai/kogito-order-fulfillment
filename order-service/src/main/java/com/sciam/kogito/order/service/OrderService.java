package com.sciam.kogito.order.service;

import com.sciam.kogito.order.model.Order;
import com.sciam.kogito.order.model.OrderItem;
import com.sciam.kogito.order.model.OrderStatus;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
@Slf4j
public class OrderService {

    @Transactional
    public Order placeOrder(Order order, KogitoProcessContext context) {
        List<OrderItem> orderItems = order.getOrderItems();
        order.setProcessInstanceId(context.getProcessInstance().getStringId());
        order.setReference(RandomStringUtils.randomAlphanumeric(8, 12).toUpperCase());
        order.setStatus(OrderStatus.WAITING_STOCK);
        order.setOrderDate(LocalDateTime.now());
        order.persistAndFlush();
        orderItems.forEach(orderItem -> {
            orderItem.setOrderId(order.getOrderId());
            orderItem.persist();
        });
        return order;
    }

    @Transactional
    public Order cancelOrder(Order order) {
        log.info("Cancelling order {}", order.getOrderId());
        return updateOrder(order.getOrderId(), OrderStatus.CANCELLED);
    }

    @Transactional
    public Order waitingShipping(Order order) {
        log.info("Completing order {}", order.getOrderId());
        return updateOrder(order.getOrderId(), OrderStatus.WAITING_SHIPPING);
    }

    @Transactional
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

    /**
     * Update stock from order.
     *
     * @param record the order
     */
    @Incoming("orders-shipping-out")
    public CompletableFuture<Void> updateStockFromOrder(Record<Integer, String> record) {
        log.info("Shipping Order : {}", record.value());
        return CompletableFuture.runAsync(() -> {
        });
    }

    public void makeException() {
        throw new RuntimeException("Exception from OrderService");
    }


}
