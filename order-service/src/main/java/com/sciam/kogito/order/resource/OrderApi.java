package com.sciam.kogito.order.resource;


import com.sciam.kogito.order.model.Order;
import com.sciam.kogito.order.model.OrderItem;
import com.sciam.kogito.order.service.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Path("/shipping/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class OrderApi {

    @Inject
    OrderService orderService;

//    @POST
//    @Path("/save")
//    public Response save(Order order) {
//        if (order == null) {
//            throw new WebApplicationException(Response.Status.BAD_REQUEST);
//        }
//        Order orderCreated = orderService.createOrder(order);
//        return Response.ok(orderCreated).build();
//    }

    @GET
    @Path("/select/{id}")
    public Response select(@PathParam("id") Integer id) {
        if (id == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        // finding a specific person by ID
        Order order = new Order();
        order = Order.findById(id);
        log.info("Order created: {}", order.getOrderItems());
        return Response.ok(order).build();
    }

    @GET
    @Path("/orderItem/{id}")
    public Response selectOrderItem(@PathParam("id") Integer id) {
        if (id == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        // finding a specific person by ID

        List<OrderItem> list = OrderItem.list("orderId", id);

        return Response.ok(list).build();
    }
}

