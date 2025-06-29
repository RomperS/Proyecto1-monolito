package com.oswaldo.proyecto1.mapper;

import com.oswaldo.proyecto1.order.dto.OrderDetailsResponse;
import com.oswaldo.proyecto1.order.model.OrderDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderDetailsMapper {

    private final ProductMapper  productMapper;

    public OrderDetailsResponse modelToResponse(OrderDetails orderDetails) {
        OrderDetailsResponse response = new OrderDetailsResponse();

        response.setId(orderDetails.getId());
        response.setOrderId(orderDetails.getOrder().getId());
        response.setProduct(productMapper.modelToInternalDTO(orderDetails.getProduct()));
        response.setQuantity(orderDetails.getQuantity());

        return response;
    }

    public List<OrderDetailsResponse> modelsToResponses(List<OrderDetails> orderDetails) {
        return orderDetails.stream().map(this::modelToResponse).collect(Collectors.toList());
    }



}
