package com.sciam.kogito.dto;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {

    private long orderId;
    private long customerId;
    private UUID transactionId;
    private Set<OrderItem> orderItems;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private String ShippingAddress;
}
