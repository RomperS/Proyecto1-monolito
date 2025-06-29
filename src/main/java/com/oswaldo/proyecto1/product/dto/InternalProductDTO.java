package com.oswaldo.proyecto1.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternalProductDTO {

    private Integer productId;

    private String productName;
    private BigDecimal price;
}
