package com.oswaldo.proyecto1.order.service;

import com.oswaldo.proyecto1.exception.OrderNotFoundException;
import com.oswaldo.proyecto1.exception.ProductNotFoundException;
import com.oswaldo.proyecto1.exception.UserNotFoundException;
import com.oswaldo.proyecto1.mapper.OrderMapper;
import com.oswaldo.proyecto1.order.dto.*;
import com.oswaldo.proyecto1.order.model.OrderDetails;
import com.oswaldo.proyecto1.order.model.Order;
import com.oswaldo.proyecto1.order.repository.OrderDetailsRepository;
import com.oswaldo.proyecto1.order.repository.OrderRepository;
import com.oswaldo.proyecto1.product.model.Product;
import com.oswaldo.proyecto1.product.repository.ProductRepository;
import com.oswaldo.proyecto1.user.repository.UserRepository;
import com.oswaldo.proyecto1.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailsRepository orderDetailsRepository;
    private static final String USERSTRING = "User";
    private static final String PRODUCTSTRING = "Product";

    @Transactional
    public OrderResponse save(OrderRequest orderRequest) {
        Order model = new Order();

        OrderDataInternalDTO odiDTO = processOrderDetails(model, orderRequest.getOrderDetailsRequests());

        model.setOrderDetails(odiDTO.getOrderDetails());
        model.setUser(userRepository.findById(orderRequest.getUser())
                .orElseThrow(() -> new UserNotFoundException(errorMessageById(USERSTRING, orderRequest.getUser())))
        );
        model.setTotalPrice(odiDTO.getTotalPrice());
        model.setDate(LocalDateTime.now());
        model.setAddress(orderRequest.getAddress());

        return orderMapper.modelToResponse(orderRepository.save(model));
    }

    public OrderResponse findById(Integer id) {
        return orderMapper.modelToResponse(getOrder(id));
    }

    public List<OrderResponse> findAll() {
        return orderMapper.modelsToResponses(orderRepository.findAll());
    }

    public List<OrderResponse> findByUser(Integer id) {
        return orderMapper.modelsToResponses(orderRepository.findByUserId(id));
    }

    public List<OrderResponse> findByDateBetween(DateBetweenDTO dateBetweenDTO) {
        return  orderMapper.modelsToResponses(orderRepository.findByDateBetween(dateBetweenDTO.getInitDate().atStartOfDay(), dateBetweenDTO.getEndDate().atTime(LocalTime.MAX)));
    }

    @Transactional
    public OrderResponse update(OrderRequest orderRequest, Integer id) {
        Order model = getOrder(id);

        OrderDataInternalDTO odiDTO = processOrderDetails(model, orderRequest.getOrderDetailsRequests());

        model.setUser(userRepository.findById(orderRequest.getUser())
                .orElseThrow(() -> new UserNotFoundException(errorMessageById(USERSTRING, orderRequest.getUser())))
        );
        model.setOrderDetails(odiDTO.getOrderDetails());
        model.setTotalPrice(odiDTO.getTotalPrice());
        model.setAddress(orderRequest.getAddress());

        return orderMapper.modelToResponse(orderRepository.save(model));
    }

    @Transactional
    public OrderResponse partialUpdate(OrderRequest orderRequest, Integer id) {
        Order model = getOrder(id);

        if (!orderRequest.getOrderDetailsRequests().isEmpty()) {
            OrderDataInternalDTO odiDTO = processOrderDetails(model, orderRequest.getOrderDetailsRequests());
            model.setOrderDetails(odiDTO.getOrderDetails());
            model.setTotalPrice(odiDTO.getTotalPrice());
        }

        if (StringUtils.hasText(orderRequest.getAddress())) {
            model.setAddress(orderRequest.getAddress());
        }

        if (orderRequest.getUser() != null){
            model.setUser(userRepository.findById(orderRequest.getUser())
                    .orElseThrow(() -> new UserNotFoundException(errorMessageById(USERSTRING, orderRequest.getUser())))
            );
        }

        return orderMapper.modelToResponse(orderRepository.save(model));
    }

    @Transactional
    public OrderResponse addDetail(OrderDetailsRequest orderDetailsRequest, Integer id) {
        Order model = getOrder(id);

        OrderDetails detail = new OrderDetails();

        detail.setOrder(model);
        detail.setQuantity(orderDetailsRequest.getQuantity());
        detail.setProduct(productRepository.findById(orderDetailsRequest.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(errorMessageById(PRODUCTSTRING, orderDetailsRequest.getProductId()))
        ));
        model.getOrderDetails().add(detail);

        return orderMapper.modelToResponse(orderRepository.save(model));
    }

    @Transactional
    public OrderResponse addDetails(List<OrderDetailsRequest> orderDetailsRequests, Integer id) {
        Order model = getOrder(id);

        for (OrderDetailsRequest request : orderDetailsRequests) {
            OrderDetails detail = new OrderDetails();
            detail.setOrder(model);
            detail.setQuantity(request.getQuantity());
            detail.setProduct(productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(errorMessageById(PRODUCTSTRING, request.getProductId())))
            );

            model.getOrderDetails().add(detail);
        }

        return orderMapper.modelToResponse(orderRepository.save(model));
    }

    @Transactional
    public void removeDetail(Integer detailId, Integer orderId) {
        Order model = getOrder(orderId);

        Optional<OrderDetails> optionalDetail = orderDetailsRepository.findById(detailId);

        if (optionalDetail.isPresent()) {
            OrderDetails detailToRemove = optionalDetail.get();

            if (!detailToRemove.getOrder().getId().equals(model.getId())) {
                throw new IllegalArgumentException("Order detail with ID " + detailId + " does not belong to order with ID " + orderId);
            }

            model.getOrderDetails().remove(detailToRemove);
            orderRepository.save(model);
        }
    }

    @Transactional
    public void removeAllDetails(Integer orderId) {
        Order model = getOrder(orderId);

        model.getOrderDetails().clear();
    }

    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

    private OrderDataInternalDTO processOrderDetails(Order model, List<OrderDetailsRequest> orderDetailsRequests) {

        List<OrderDetails> details = new ArrayList<>();
        BigDecimal totalPrice = new  BigDecimal(0);

        for (OrderDetailsRequest request : orderDetailsRequests) {
            Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new
                    ProductNotFoundException(errorMessageById(PRODUCTSTRING, request.getProductId()))
            );

            OrderDetails detail = new OrderDetails();
            detail.setProduct(product);
            detail.setOrder(model);
            detail.setQuantity(request.getQuantity());
            details.add(detail);

            totalPrice = totalPrice.add(
                    BigDecimal.valueOf(request.getQuantity()).multiply(product.getPrice())
            );
        }

        OrderDataInternalDTO dto = new OrderDataInternalDTO();
        dto.setOrderDetails(details);
        dto.setTotalPrice(totalPrice);

        return dto;

    }

    private Order getOrder(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(errorMessageById(USERSTRING, id)));
    }

    private String errorMessageById(String entity, Integer id) {
        return String.format(ErrorMessages.ENTITY_WITH_ID_NOT_FOUND, entity, id);
    }
}
