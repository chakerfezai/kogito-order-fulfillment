package com.sciam.kogito.service;

import com.sciam.kogito.dto.Order;
import com.sciam.kogito.dto.Payment;
import com.sciam.kogito.dto.PaymentStatus;
import com.sciam.kogito.dto.Shipping;
import io.smallrye.reactive.messaging.ce.OutgoingCloudEventMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@ApplicationScoped
@Slf4j
public class KafkaOrdersProducer {

    @Inject
    @Channel("orders-start-in")
    Emitter<Order> orderEmitter;

    @Inject
    @Channel("orders-payment-in")
    Emitter<Payment> paymentEmitter;

    @Inject
    @Channel("orders-shipping-in")
    Emitter<Shipping> shippingEmitter;

    public void publishOrder(Order order) {
        OutgoingCloudEventMetadata<Object> eventMetadata = null;
        log.info("Start Publishing request order {}, metadata {}",order , eventMetadata);
        try {
            eventMetadata = OutgoingCloudEventMetadata.builder()
                    .withId("id-" + order.getTransactionId())
                    .withSpecVersion("1.0")
                    .withTimestamp(ZonedDateTime.now())
                    .withSource(new URI("orders-start-in"))
                    .withType("orders-start-in")
                    .build();
            orderEmitter.send(Message.of(order).addMetadata(eventMetadata));
            log.info("Publishing request order {}, metadata {}", order, eventMetadata);
        } catch (Exception exception) {
            throw new RuntimeException();
        }

    }

    public void publishPayment(Payment payment) {
        OutgoingCloudEventMetadata<Object> eventMetadata = null;
        log.info("Start Publishing request payment {}, metadata {}", payment);
        try {
            eventMetadata = OutgoingCloudEventMetadata.builder()
                    .withId("id-" + payment.getTransactionId())
                    .withSpecVersion("1.0")
                    .withTimestamp(ZonedDateTime.now())
                    .withSource(new URI("orders-payment-in"))
                    .withType("orders-payment-in")
                    .build();
            payment.setPaymentDate(LocalDateTime.now());
            payment.setPaymentStatus(PaymentStatus.PENDING);
            log.info("Publishing request payment {}, metadata {}", payment, eventMetadata);
            paymentEmitter.send(Message.of(payment).addMetadata(eventMetadata));
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }


    public void publishShipping(Shipping shipping) {
        OutgoingCloudEventMetadata<Object> eventMetadata = null;
        log.info("Start Publishing request shipping {}, metadata {}", shipping, eventMetadata);
        try {
            eventMetadata = OutgoingCloudEventMetadata.builder()
                    .withId("id-" + shipping.getOrderId())
                    .withSpecVersion("1.0")
                    .withTimestamp(ZonedDateTime.now())
                    .withSource(new URI("orders-shipping-in"))
                    .withType("orders-shipping-in")
                    .build();

            log.info("Publishing request shipping {}, metadata {}", shipping, eventMetadata);
            shippingEmitter.send(Message.of(shipping).addMetadata(eventMetadata));
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }

}
