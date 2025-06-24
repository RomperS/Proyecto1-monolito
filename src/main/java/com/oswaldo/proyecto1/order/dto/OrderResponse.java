package com.oswaldo.proyecto1.order.dto;

import com.oswaldo.proyecto1.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Integer id;
    private List<OrderDetailsResponse> orderDetails = new ArrayList<>();
    private double totalPrice;
    private LocalDateTime date;
    private String address;
}
