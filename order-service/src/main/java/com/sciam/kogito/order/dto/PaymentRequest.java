package com.sciam.kogito.order.dto;


import com.sciam.kogito.order.model.PaymentStatus;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest extends PanacheEntity implements Serializable {
    private long orderId;
    private String processInstanceId;
}
