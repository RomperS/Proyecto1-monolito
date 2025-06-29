package com.oswaldo.proyecto1.order.service;

import com.oswaldo.proyecto1.exception.OrderDetailsNotFoundException;
import com.oswaldo.proyecto1.exception.OrderNotFoundException;
import com.oswaldo.proyecto1.exception.ProductNotFoundException;
import com.oswaldo.proyecto1.mapper.OrderDetailsMapper;
import com.oswaldo.proyecto1.order.dto.OrderDetailsRequest;
import com.oswaldo.proyecto1.order.dto.OrderDetailsResponse;
import com.oswaldo.proyecto1.order.model.OrderDetails;
import com.oswaldo.proyecto1.order.repository.OrderDetailsRepository;
import com.oswaldo.proyecto1.order.repository.OrderRepository;
import com.oswaldo.proyecto1.product.repository.ProductRepository;
import com.oswaldo.proyecto1.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private static final String ORDERSTRING = "Order";
    private static final String PRODUCTSTRING = "Product";

    @Transactional
    public OrderDetailsResponse save(OrderDetailsRequest orderDetailsRequest) {
        OrderDetails model = new OrderDetails();

        model.setOrder(orderRepository.findById(orderDetailsRequest.getOrderId())
                .orElseThrow(() -> new  OrderNotFoundException(errorMessageById(ORDERSTRING, orderDetailsRequest.getOrderId())))
        );

        model.setProduct(productRepository.findById(orderDetailsRequest.getProductId())
                .orElseThrow(() -> new  ProductNotFoundException(errorMessageById(PRODUCTSTRING, orderDetailsRequest.getProductId())))
        );

        orderDetailsRepository.save(model);

        return orderDetailsMapper.modelToResponse(model);
    }

    public OrderDetailsResponse findById(Integer id) {
        return orderDetailsMapper.modelToResponse(orderDetailsRepository.findById(id)
                .orElseThrow(() -> new OrderDetailsNotFoundException(errorMessageById(ORDERSTRING, id)))
        );
    }

    public List<OrderDetailsResponse> findAll() {
        return orderDetailsMapper.modelsToResponses(orderDetailsRepository.findAll());
    }

    public List<OrderDetailsResponse> findAllByOrderId(Integer id) {
        return orderDetailsMapper.modelsToResponses(orderDetailsRepository.findByOrderId(id));
    }

    @Transactional
    public OrderDetailsResponse update(OrderDetailsRequest orderDetailsRequest, Integer id) {
        OrderDetails model = orderDetailsRepository.findById(id).orElseThrow(() ->
                new OrderDetailsNotFoundException(errorMessageById("Order details", id))
        );

        model.setOrder(orderRepository.findById(orderDetailsRequest.getOrderId()).orElseThrow(() ->
                        new OrderNotFoundException(errorMessageById(ORDERSTRING, id)))
        );

        model.setProduct(productRepository.findById(orderDetailsRequest.getProductId()).orElseThrow(() ->
                new ProductNotFoundException(errorMessageById(PRODUCTSTRING, id)))
        );

        model.setQuantity(orderDetailsRequest.getQuantity());

        return orderDetailsMapper.modelToResponse(orderDetailsRepository.save(model));
    }

    public void delete(Integer id) {
        orderDetailsRepository.deleteById(id);
    }

    private String errorMessageById(String entity, Integer id) {
        return String.format(ErrorMessages.ENTITY_WITH_ID_NOT_FOUND, entity, id);
    }
}
