package com.oswaldo.proyecto1.product.controller;

import com.oswaldo.proyecto1.product.dto.PriceRangeDTO;
import com.oswaldo.proyecto1.product.dto.ProductRequest;
import com.oswaldo.proyecto1.product.dto.ProductResponse;
import com.oswaldo.proyecto1.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest product) {
        return service.save(product);
    }

    @GetMapping("/id")
    public ProductResponse getById(@RequestParam Integer id) {
        return service.findById(id);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/name")
    public List<ProductResponse> findByName(@RequestParam String name) {
        return service.findByName(name);
    }

    @GetMapping("/price")
    public List<ProductResponse> findByPrice(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        return service.findByPrice(new PriceRangeDTO(minPrice, maxPrice));
    }

    @PutMapping
    public ProductResponse update(@RequestBody ProductRequest product, @RequestParam Integer id) {
        return service.update(product, id);
    }

    @PatchMapping
    public ProductResponse partialUpdate(@RequestBody ProductRequest product, @RequestParam Integer id) {
        return service.partialUpdate(product, id);
    }

    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        service.delete(id);
    }

}
