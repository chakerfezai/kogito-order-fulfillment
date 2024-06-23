package com.sciam.kogito.order.dto;

import com.sciam.kogito.order.model.OrderItem;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
public class InventoryOrder {

    private long orderId;
    private String processInstanceId;
    private List<OrderItem> orderItems;

}
