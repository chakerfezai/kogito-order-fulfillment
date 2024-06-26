package com.sciam.kogito.resource;

import com.sciam.kogito.shop.entity.Customer;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path = "/customer")
public interface CustomerResource extends PanacheEntityResource<Customer, Long> {
}
