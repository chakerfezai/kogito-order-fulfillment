package com.sciam.kogito.dto;

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
