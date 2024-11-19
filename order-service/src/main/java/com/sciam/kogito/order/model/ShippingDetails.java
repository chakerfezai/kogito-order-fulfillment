package com.sciam.kogito.order.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
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
@Table(name = "shipping_details")
public class ShippingDetails extends PanacheEntityBase implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingDetailsId;
    private String shippingMethod;
    private LocalDateTime shippingDate;
    private String shippingAddress;
    private ShippingStatus shippingStatus;
    @OneToOne(mappedBy = "shippingDetails")
    private Order order;
}
