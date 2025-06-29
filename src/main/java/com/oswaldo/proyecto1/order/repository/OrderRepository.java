package com.oswaldo.proyecto1.order.repository;

import com.oswaldo.proyecto1.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(Integer userId);
    List<Order> findByDateBetween(LocalDateTime minDate, LocalDateTime maxDate);
}
