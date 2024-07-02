package com.sciam.kogito.dto;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    private UUID transactionId;
    private double amount;
    private LocalDateTime paymentDate;
    private long orderId;
    private String countryOrigin;
    private String countryDestination;
    private PaymentMethod method;
    private PaymentStatus paymentStatus;
    private String reason;
    private String processInstanceId;
}
