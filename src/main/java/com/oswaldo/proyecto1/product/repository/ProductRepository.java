package com.oswaldo.proyecto1.product.repository;

import com.oswaldo.proyecto1.product.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    List<ProductModel> findByNameContainingIgnoreCase(String name);
    List<ProductModel> findByPriceBetween(double minPrice, double maxPrice);
    boolean existsByIdAndStockGreaterThan(Integer productId, int minStock);
    boolean existsByNameAndIdIsNot(String name, Integer productId);
    boolean existByName (String name);
}
