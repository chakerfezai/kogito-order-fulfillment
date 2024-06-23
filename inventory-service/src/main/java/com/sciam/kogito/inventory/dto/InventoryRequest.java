package com.sciam.kogito.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {

    private long orderId;
    private String processInstanceId;
    private List<OrderItem> orderItems;


}
