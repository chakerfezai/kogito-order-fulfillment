package com.sciam.kogito.order.model;

public record ShippingDetails(Address address, Receiver receiver, ShippingMethod shippingMethod) {
}
