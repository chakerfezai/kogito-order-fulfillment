package com.sciam.kogito.resource;

import com.sciam.kogito.shop.entity.Address;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path = "/address")
public interface AddressResource extends PanacheEntityResource<Address,Long> {
}
