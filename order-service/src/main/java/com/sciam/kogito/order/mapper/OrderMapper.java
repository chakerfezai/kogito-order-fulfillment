package com.sciam.kogito.order.mapper;


import com.sciam.kogito.order.dto.OrderDto;
import com.sciam.kogito.order.model.Order;
import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

@Mapper(componentModel = "cdi")
public interface OrderMapper {

    OrderDto toOrderDto(Order order);

    Order fromOrderDto(OrderDto orderDto);
}
