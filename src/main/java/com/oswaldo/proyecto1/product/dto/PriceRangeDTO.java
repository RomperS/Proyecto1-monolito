package com.oswaldo.proyecto1.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceRangeDTO {

    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}