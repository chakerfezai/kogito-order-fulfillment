package com.sciam.kogito.inventory.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Product")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends PanacheEntity {
    private String productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;


    public static Product findProductId(String productId){
        return find("productId", productId).firstResult();
    }

}
