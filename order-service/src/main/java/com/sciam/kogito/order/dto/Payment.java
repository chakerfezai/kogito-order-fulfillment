package com.sciam.kogito.order.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Payment {

    private double amount;
    private LocalDateTime paymentDate;
    private long orderId;
    private String countryOrigin;
    private String countryDestination;
    private String processInstanceId;
}
