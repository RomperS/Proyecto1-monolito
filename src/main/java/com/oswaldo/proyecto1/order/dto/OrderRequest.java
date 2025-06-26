package com.oswaldo.proyecto1.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private List<OrderDetailsRequest> orderDetailsRequests = new ArrayList<>();
    private Integer user;
    private String address;
}
