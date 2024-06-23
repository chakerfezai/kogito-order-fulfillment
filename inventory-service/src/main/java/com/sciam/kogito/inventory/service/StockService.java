package com.sciam.kogito.inventory.service;

import com.google.gson.Gson;
import com.sciam.kogito.inventory.dto.InventoryRequest;
import com.sciam.kogito.inventory.dto.InventoryStatus;
import com.sciam.kogito.inventory.dto.OrderItem;
import com.sciam.kogito.inventory.entity.Product;
import io.smallrye.reactive.messaging.ce.OutgoingCloudEventMetadata;
import io.smallrye.reactive.messaging.kafka.Record;
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
import java.util.concurrent.CompletableFuture;

/**
 * The type Stock service.
 */
@ApplicationScoped
@Slf4j
public class StockService {


    /**
     * The Emitter.
     */
    @Inject
    @Channel("orders-stock-out")
    Emitter<String> emitter;


    /**
     * Update stock from order.
     *
     * @param record the order
     */
    @Incoming("orders-stock-in")
    public CompletableFuture<Void> updateStockFromOrder(Record<Integer, String> record) {
        Gson gson = new Gson();
        InventoryRequest inventoryOrder = gson.fromJson(record.value(), InventoryRequest.class);
        log.info("Updating stock for {} , {}", record.key(), inventoryOrder);
        String updateStockStatus = updateStock(inventoryOrder);
        publishOrder(inventoryOrder, updateStockStatus);
        return CompletableFuture.runAsync(() -> {
        });
    }

    @Transactional
    public String updateStock(InventoryRequest inventoryOrder) {
        try {
            inventoryOrder.getOrderItems().forEach(this::updateProduct);
            return InventoryStatus.RESERVED.name();
        } catch (Exception e) {
            log.error("Error updating stock", e);
            return InventoryStatus.FAILED.name();
        }


    }


    public void publishOrder(InventoryRequest inventoryOrder, String updateStockStatus) {
        OutgoingCloudEventMetadata<Object> eventMetadata = null;
        Map<String, Object> extensions = new HashMap<>();
        extensions.put("kogitoprocrefid", inventoryOrder.getProcessInstanceId());
        try {
            eventMetadata = OutgoingCloudEventMetadata.builder()
                    .withId("id-" + inventoryOrder.getOrderId())
                    .withSpecVersion("1.0")
                    .withTimestamp(ZonedDateTime.now())
                    .withSource(new URI("orders-stock-out"))
                    .withType("orders-stock-out")
                    .withExtensions(extensions)
                    .build();
            log.info("Publishing response to order {}, metadata {}", updateStockStatus, inventoryOrder.getProcessInstanceId());
            emitter.send(Message.of(updateStockStatus).addMetadata(eventMetadata));
        } catch (Exception exception) {
            throw new RuntimeException();
        }

    }

    private void updateProduct(OrderItem orderItem) {
        this.updateProductStock(orderItem.getProductId(), orderItem.getQuantity());
    }

    private void updateProductStock(String productId, int itemQuantity) {
        Product product1 = Product.findProductId(productId);
        log.info("Updating stock from {} , {}", productId, itemQuantity);
        int quantity = product1.getStockQuantity() - itemQuantity;
        product1.setStockQuantity(quantity);
        log.info("Updating stock from {} , {}", productId, quantity);
        product1.persist();
    }
}
