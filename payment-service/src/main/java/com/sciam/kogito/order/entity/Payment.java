package com.sciam.kogito.order.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Payment extends PanacheEntity implements Serializable {
    private double amount;
    private LocalDateTime paymentDate;
    private long orderId;
    private String country;
    private PaymentMethod method;
    private PaymentStatus paymentStatus = PaymentStatus.WAITING_PAYMENT;
}
