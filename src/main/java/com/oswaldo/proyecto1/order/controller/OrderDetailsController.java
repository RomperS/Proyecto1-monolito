package com.oswaldo.proyecto1.order.controller;


import com.oswaldo.proyecto1.order.dto.OrderDetailsRequest;
import com.oswaldo.proyecto1.order.dto.OrderDetailsResponse;
import com.oswaldo.proyecto1.order.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-details")
public class OrderDetailsController {

    private final OrderDetailsService service;

    @PostMapping
    public OrderDetailsResponse save(@RequestBody OrderDetailsRequest request) {
        return service.save(request);
    }

    @GetMapping("/{id}")
    public OrderDetailsResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping
    public List<OrderDetailsResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/order/{orderId}")
    public List<OrderDetailsResponse> findAllByOrderId(@PathVariable Integer orderId) {
        return service.findAllByOrderId(orderId);
    }

    @PutMapping("/{id}")
    public OrderDetailsResponse update(@RequestBody OrderDetailsRequest request, @PathVariable Integer id) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}