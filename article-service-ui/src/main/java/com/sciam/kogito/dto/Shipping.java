package com.sciam.kogito.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {

    private String orderId;
    private String processInstanceId;
    private String status;
}
