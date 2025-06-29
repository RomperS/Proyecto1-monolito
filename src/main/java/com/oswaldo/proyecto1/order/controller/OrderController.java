package com.oswaldo.proyecto1.order.controller;

import com.oswaldo.proyecto1.order.dto.DateBetweenDTO;
import com.oswaldo.proyecto1.order.dto.OrderDetailsRequest;
import com.oswaldo.proyecto1.order.dto.OrderRequest;
import com.oswaldo.proyecto1.order.dto.OrderResponse;
import com.oswaldo.proyecto1.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public OrderResponse save(@RequestBody OrderRequest request) {
        return service.save(request);
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping
    public List<OrderResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> findByUser(@PathVariable Integer userId) {
        return service.findByUser(userId);
    }

    @PostMapping("/date")
    public List<OrderResponse> findByDateBetween(@RequestBody DateBetweenDTO dto) {
        return service.findByDateBetween(dto);
    }

    @PutMapping("/{id}")
    public OrderResponse update(@RequestBody OrderRequest request, @PathVariable Integer id) {
        return service.update(request, id);
    }

    @PatchMapping("/{id}")
    public OrderResponse partialUpdate(@RequestBody OrderRequest request, @PathVariable Integer id) {
        return service.partialUpdate(request, id);
    }

    @PostMapping("/{id}/detail")
    public OrderResponse addDetail(@RequestBody OrderDetailsRequest request, @PathVariable Integer id) {
        return service.addDetail(request, id);
    }

    @PostMapping("/{id}/details")
    public OrderResponse addDetails(@RequestBody List<OrderDetailsRequest> requests, @PathVariable Integer id) {
        return service.addDetails(requests, id);
    }

    @DeleteMapping("/{orderId}/detail/{detailId}")
    public void removeDetail(@PathVariable Integer detailId, @PathVariable Integer orderId) {
        service.removeDetail(detailId, orderId);
    }

    @DeleteMapping("/{orderId}/details")
    public void removeAllDetails(@PathVariable Integer orderId) {
        service.removeAllDetails(orderId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
