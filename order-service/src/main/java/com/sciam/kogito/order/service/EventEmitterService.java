package com.sciam.kogito.order.service;

import com.sciam.kogito.order.dto.InventoryOrder;
import com.sciam.kogito.order.dto.OrderDto;
import com.sciam.kogito.order.dto.Payment;
import com.sciam.kogito.order.mapper.OrderMapper;
import com.sciam.kogito.order.model.InventoryStatus;
import com.sciam.kogito.order.model.Order;
import com.sciam.kogito.order.model.OrderStatus;
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

    @Inject
    OrderMapper orderMapper;



    public OrderDto sendInventory(OrderDto orderDto) {
        InventoryOrder inventoryOrder = InventoryOrder.builder()
                .orderId(orderDto.getOrderId())
                .orderItems(orderDto.getOrderItems())
                .processInstanceId(orderDto.getProcessInstanceId())
                .build();
        emitter.send(Record.of((int) inventoryOrder.getOrderId(), inventoryOrder));
        return orderDto;
    }


    @Transactional
    public OrderDto createPayment(OrderDto orderDto, String inventoryStatus) {
        Order orderToUpdate = Order.findById(orderDto.getOrderId());
        orderToUpdate.setInventoryStatus(InventoryStatus.valueOf(inventoryStatus));
        orderToUpdate.persistAndFlush();

        Payment payment = Payment.builder()
                .transactionId(orderToUpdate.getTransactionId())
                .orderId(orderToUpdate.getOrderId())
                .amount(orderToUpdate.getTotalAmount())
                .processInstanceId(orderToUpdate.getProcessInstanceId())
                .countryOrigin(orderToUpdate.getCountryOrigin())
                .countryDestination(orderToUpdate.getCountryDestination())
                .build();
        try {
            log.info("Payment call payment service for {}", orderToUpdate);
            Response response = paymentProxy.create(payment);
            long paymentId = response.readEntity(Long.class);
            log.info("Payment created for {} , {}", orderToUpdate.getOrderId(), paymentId);
            orderToUpdate.setStatus(OrderStatus.WAITING_PAYMENT);
            orderToUpdate.setPaymentStatus(PaymentStatus.PENDING);
            orderToUpdate.setPaymentId(paymentId);
            orderToUpdate.persistAndFlush();
            return orderMapper.toOrderDto(orderToUpdate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}