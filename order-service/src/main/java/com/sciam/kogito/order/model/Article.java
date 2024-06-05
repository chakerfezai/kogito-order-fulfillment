package com.sciam.kogito.order.model;

import java.util.UUID;

public record Article(long id, String name, String image, double price) {
}
