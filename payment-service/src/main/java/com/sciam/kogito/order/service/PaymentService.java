package com.sciam.kogito.order.service;


import com.sciam.kogito.order.entity.Payment;
import com.sciam.kogito.order.entity.PaymentStatus;
import com.sciam.kogito.order.payload.CheckFraud;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.reactive.messaging.ce.OutgoingCloudEventMetadata;
import io.smallrye.reactive.messaging.kafka.Record;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class PaymentService {


    public Payment create(Payment payment) {
        payment.persist();
        return payment;
    }


    public Payment get(UUID transactionId) {
        return Payment.findByTransactionId(transactionId);
    }


    @Inject
    @Channel("orders-payment-out")
    Emitter<String> emitter;

    @Inject
    EventBus bus;

    @Transactional
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
            switch (checkFraudStatus) {
                case "REJECTED":
                    payment.setPaymentStatus(PaymentStatus.FAILED_PAYMENT);
                case "APPROVED":
                    payment.setPaymentStatus(PaymentStatus.SUCCESSFUL_PAYMENT);
            }
            bus.send("payment", payment);
            log.info("Publishing response to order {}, metadata {}", checkFraudStatus, payment.getProcessInstanceId());
            emitter.send(Message.of(checkFraudStatus).addMetadata(eventMetadata));
        } catch (Exception exception) {
            throw new RuntimeException();
        }

    }


    /**
     * Update stock from order.
     *
     * @param record the order
     */
    @Incoming("orders-payment-cancel")
    public void cancelPayment(Record<Integer, Long> record) {
        Long paymentId = record.value();
        log.info("receive cancel payment {} , {}", record.key(), paymentId);
        this.cancel(paymentId);
    }

    private void cancel(long paymentId) {
        Optional<Payment> payment = Payment.findByIdOptional(paymentId);
        if (payment.isPresent()) {
            Payment payment1 = payment.get();
            payment1.setPaymentStatus(PaymentStatus.CANCELLED);
            payment1.persist();
        }
    }


    @ConsumeEvent(value = "payment", blocking = true)
    @Transactional
    public void consume(Payment payment) {
        log.info("receive payment {}", payment.getProcessInstanceId());
        payment.persist();
    }

}
