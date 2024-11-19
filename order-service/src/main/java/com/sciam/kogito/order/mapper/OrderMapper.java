package com.sciam.kogito.order.mapper;


import com.sciam.kogito.order.dto.OrderDto;
import com.sciam.kogito.order.model.Order;
import org.mapstruct.Mapper;

@Mapper(config = OrderMappingConfig.class)
public interface OrderMapper {

    OrderDto toOrderDto(Order order);

    Order fromOrderDto(OrderDto orderDto);
}
