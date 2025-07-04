package com.oswaldo.proyecto1.product.repository;

import com.oswaldo.proyecto1.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    boolean existsByNameAndIdIsNot(String name, Integer productId);
}
