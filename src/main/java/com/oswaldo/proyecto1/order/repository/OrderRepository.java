package com.oswaldo.proyecto1.order.repository;

import com.oswaldo.proyecto1.order.model.OrderModel;
import com.oswaldo.proyecto1.product.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
    List<OrderModel> findByUserId(Integer userId);
    List<OrderModel> findByDateBetween(LocalDateTime minDate, LocalDateTime maxDate);
}
