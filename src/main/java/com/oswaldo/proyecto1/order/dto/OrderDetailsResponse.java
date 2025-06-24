package com.oswaldo.proyecto1.order.dto;

import com.oswaldo.proyecto1.product.dto.InternalProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsResponse {

    private Integer id;
    private Integer orderId;
    private InternalProductDTO product;
    private double price;
}
