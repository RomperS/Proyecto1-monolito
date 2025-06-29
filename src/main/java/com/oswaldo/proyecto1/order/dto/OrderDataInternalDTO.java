package com.oswaldo.proyecto1.order.dto;

import com.oswaldo.proyecto1.order.model.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDataInternalDTO {

    private BigDecimal totalPrice;
    private List<OrderDetails>  orderDetails;
}
