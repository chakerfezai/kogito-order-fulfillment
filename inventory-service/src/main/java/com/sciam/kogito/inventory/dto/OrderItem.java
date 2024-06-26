package com.sciam.kogito.inventory.dto;


import com.sciam.kogito.inventory.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem implements Serializable {
    private Product product;
    private String productId;
    private int quantity;
    private Double amount;
}
