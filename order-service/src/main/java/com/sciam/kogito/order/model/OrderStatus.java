package com.sciam.kogito.order.model;

public enum OrderStatus {
    PENDING,
    WAITING_STOCK,
    WAITING_PAYMENT,
    WAITING_SHIPPING,
    WAITING_DELIVERY,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
