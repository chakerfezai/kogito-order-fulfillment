package com.sciam.kogito.order.service;

import com.sciam.kogito.order.entity.Payment;
import com.sciam.kogito.order.payload.CheckFraud;
import io.smallrye.reactive.messaging.ce.OutgoingCloudEventMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@Slf4j
public class PaymentService {


    public Payment create(Payment payment) {
        payment.persist();
        return payment;
    }

    /**
     * The Emitter.
     */
    @Inject
    @Channel("orders-payment-out")
    Emitter<String> emitter;


    public void publishOrder(Payment payment, CheckFraud checkFraud) {
        OutgoingCloudEventMetadata<Object> eventMetadata = null;
        Map<String, Object> extensions = new HashMap<>();
        extensions.put("kogitoprocrefid", payment.getProcessInstanceId());
        try {
            eventMetadata = OutgoingCloudEventMetadata.builder()
                    .withId("id-" + payment.getOrderId())
                    .withSpecVersion("1.0")
                    .withTimestamp(ZonedDateTime.now())
                    .withSource(new URI("orders-payment-out"))
                    .withType("orders-payment-out")
                    .withExtensions(extensions)
                    .build();
            String checkFraudStatus = checkFraud.getStatus();
            log.info("Publishing response to order {}, metadata {}", checkFraudStatus, payment.getProcessInstanceId());
            emitter.send(Message.of(checkFraudStatus).addMetadata(eventMetadata));
        } catch (Exception exception) {
            throw new RuntimeException();
        }

    }

}
