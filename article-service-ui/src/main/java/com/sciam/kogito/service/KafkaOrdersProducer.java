package com.sciam.kogito.service;

import com.sciam.kogito.dto.Order;
import io.smallrye.reactive.messaging.ce.OutgoingCloudEventMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.net.URI;
import java.time.ZonedDateTime;

@ApplicationScoped
public class KafkaOrdersProducer {

    @Inject
    @Channel("orders-start-in")
    Emitter<Order> emitter;

    public void publishOrder(Order order) {
        OutgoingCloudEventMetadata<Object> eventMetadata = null;
        try {
            eventMetadata = OutgoingCloudEventMetadata.builder()
                    .withId("id-" +order.getTransactionId())
                    .withSpecVersion("1.0")
                    .withTimestamp(ZonedDateTime.now())
                    .withSource(new URI("orders-start-in"))
                    .withType("orders-start-in")
                    .build();
            emitter.send(Message.of(order).addMetadata(eventMetadata));
        }catch (Exception exception) {
            throw new RuntimeException();
        }

    }

}
