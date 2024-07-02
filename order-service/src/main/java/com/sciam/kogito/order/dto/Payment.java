package com.sciam.kogito.order.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private UUID transactionId;
    private double amount;
    private LocalDateTime paymentDate;
    private long orderId;
    private String countryOrigin;
    private String countryDestination;
    private String processInstanceId;
}
