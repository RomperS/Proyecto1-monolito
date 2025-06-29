package com.oswaldo.proyecto1.mapper;

import com.oswaldo.proyecto1.order.dto.OrderResponse;
import com.oswaldo.proyecto1.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderDetailsMapper orderDetailsMapper;


    public OrderResponse modelToResponse(Order order) {
        OrderResponse response = new OrderResponse();

        response.setOrderDetails(orderDetailsMapper.modelsToResponses(order.getOrderDetails()));
        response.setId(order.getId());
        response.setDate(order.getDate());
        response.setTotalPrice(order.getTotalPrice());
        response.setAddress(order.getAddress());

        return response;
    }

    public List<OrderResponse> modelsToResponses(List<Order> orders) {
        return orders.stream().map(this::modelToResponse).collect(Collectors.toList());
    }

}
