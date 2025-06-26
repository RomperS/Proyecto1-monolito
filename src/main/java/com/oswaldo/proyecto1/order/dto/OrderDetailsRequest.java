package com.oswaldo.proyecto1.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsRequest {

    private Integer orderId;
    private Integer productId;
    private int quantity;
}
