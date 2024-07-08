package com.sciam.kogito.order.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Schema(name = "Order", description = "shopping cart details")
public class Order extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private long customerId;
    @Transient
    private List<OrderItem> orderItems;
    private Double totalAmount;
    @Builder.Default
    private LocalDateTime orderDate = LocalDateTime.now();
    private OrderStatus status = OrderStatus.PENDING;
    private InventoryStatus inventoryStatus;
    private long paymentId;
    private PaymentStatus paymentStatus;
    private String ShippingAddress;
    private UUID transactionId;
    private String reference;
    private String processInstanceId;
    private String countryOrigin;
    private String countryDestination;
    @OneToOne
    @JoinColumn(name = "shippingDetailsId")
    private ShippingDetails shippingDetails;
}
