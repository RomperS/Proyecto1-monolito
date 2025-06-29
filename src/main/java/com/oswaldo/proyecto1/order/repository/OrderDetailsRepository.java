package com.oswaldo.proyecto1.order.repository;

import com.oswaldo.proyecto1.order.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByOrderId(Integer orderId);
    Optional<OrderDetails> findByOrderIdAndProductIdAndQuantity(Integer orderId, Integer productId, int quantity);

}
