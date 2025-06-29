package com.oswaldo.proyecto1.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Internal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private InternalProductDTO internalProductDTO;
    private int stock;
}
