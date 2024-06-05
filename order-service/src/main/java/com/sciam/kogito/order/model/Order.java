package com.sciam.kogito.order.model;

import java.time.LocalDateTime;

public record Order(LocalDateTime creationDate, Article article, long paymentId, ShippingDetails shipping) {

}
