package com.sciam.kogito.order.model;

public record OrderInfo(Order order, ShippingStatus shippingStatus, PaymentStatus paymentStatus,
                        String trackingNumber) {
}
