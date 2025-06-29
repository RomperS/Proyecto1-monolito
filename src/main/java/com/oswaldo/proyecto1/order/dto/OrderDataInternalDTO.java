package com.oswaldo.proyecto1.order.dto;

import com.oswaldo.proyecto1.order.model.OrderDetailsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDataInternalDTO {

    private double totalPrice;
    private List<OrderDetailsModel>  orderDetails;
}
