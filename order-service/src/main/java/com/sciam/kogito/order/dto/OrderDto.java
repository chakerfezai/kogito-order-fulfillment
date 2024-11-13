package com.sciam.kogito.order.dto;

import com.sciam.kogito.order.model.*;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderDto {


    private long orderId;
    private long customerId;
    private List<OrderItem> orderItems;
    private Double totalAmount;
    private LocalDateTime orderDate = LocalDateTime.now();
    private OrderStatus status ;
    private InventoryStatus inventoryStatus;
    private long paymentId;
    private PaymentStatus paymentStatus;
    private String ShippingAddress;
    private String transactionId;
    private String reference;
    private String processInstanceId;
    private String countryOrigin;
    private String countryDestination;
    private ShippingDetails shippingDetails;
}
