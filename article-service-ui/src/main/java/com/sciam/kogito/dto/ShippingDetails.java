package com.sciam.kogito.dto;

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
public class ShippingDetails implements Serializable {

    private Long shippingDetailsId;
    private String shippingMethod;
    private LocalDateTime shippingDate;
    private String shippingAddress;
    private ShippingStatus shippingStatus;
}
