package com.sciam.kogito.inventory.resource;

import com.sciam.kogito.inventory.entity.Product;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path = "/product")
public interface ProductResource extends PanacheEntityResource<Product,Long> {
}
