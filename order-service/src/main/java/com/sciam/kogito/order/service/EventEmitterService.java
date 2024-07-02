package com.sciam.kogito.order.service;

import com.sciam.kogito.order.dto.InventoryOrder;
import com.sciam.kogito.order.dto.Payment;
import com.sciam.kogito.order.model.InventoryStatus;
import com.sciam.kogito.order.model.Order;
import com.sciam.kogito.order.model.PaymentStatus;
import com.sciam.kogito.order.proxy.PaymentProxy;
import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class EventEmitterService {


    private static final Logger log = LoggerFactory.getLogger(EventEmitterService.class);
    @Inject
    @RestClient
    PaymentProxy paymentProxy;

    @Inject
    @Channel("orders-stock-in")
    Emitter<Record<Integer, InventoryOrder>> emitter;

    public Order sendInventory(Order order) {
        InventoryOrder inventoryOrder = InventoryOrder.builder()
                .orderId(order.getOrderId())
                .orderItems(order.getOrderItems())
                .processInstanceId(order.getProcessInstanceId())
                .build();
        emitter.send(Record.of((int) inventoryOrder.getOrderId(), inventoryOrder));
        return order;
    }

    @Transactional
    public Order createPayment(Order order, String inventoryStatus) {
        Order orderToUpdate = Order.findById(order.getOrderId());
        orderToUpdate.setInventoryStatus(InventoryStatus.valueOf(inventoryStatus));
        orderToUpdate.persistAndFlush();
        log.info("Payment created for {}", orderToUpdate);
        Payment payment = Payment.builder()
                .transactionId(order.getTransactionId())
                .orderId(orderToUpdate.getOrderId())
                .amount(orderToUpdate.getTotalAmount())
                .processInstanceId(orderToUpdate.getProcessInstanceId())
                .countryOrigin(orderToUpdate.getCountryOrigin())
                .countryDestination(orderToUpdate.getCountryDestination())
                .build();
        try {
            log.info("payment : {}", payment);
            Response response = paymentProxy.create(payment);
            long paymentId = response.readEntity(Long.class);
            orderToUpdate.setPaymentStatus(PaymentStatus.PENDING);
            orderToUpdate.setPaymentId(paymentId);
            orderToUpdate.persistAndFlush();
            return orderToUpdate;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
