package com.sciam.kogito.order.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderPaymentRef extends PanacheEntity {
    private long orderId;
    private long paymentId;
    private String status;
    private String processRefId;
}
