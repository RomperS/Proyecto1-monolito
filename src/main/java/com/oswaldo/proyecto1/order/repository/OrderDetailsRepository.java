package com.oswaldo.proyecto1.order.repository;

import com.oswaldo.proyecto1.order.model.OrderDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsModel, Integer> {
    List<OrderDetailsModel> findByOrderId(Integer orderId);
    List<OrderDetailsModel> findByProductId(Integer productId);
}
