package com.sciam.kogito.order.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sciam.kogito.order.model.OrderItem;
import com.sciam.kogito.order.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = OrderDtoDeserializer.class)
public class OrderDto implements Serializable {


    private long orderId;
    private long customerId;
    private List<OrderItem> orderItems;
    private Double totalAmount;
    private Date orderDate;
    private OrderStatus status;
    private long paymentId;
    private String ShippingAddress;
    private String transactionId;
    private String reference;
    private String processInstanceId;
    private String countryOrigin;
    private String countryDestination;
}
