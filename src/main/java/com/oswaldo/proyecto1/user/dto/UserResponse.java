package com.oswaldo.proyecto1.user.dto;

import com.oswaldo.proyecto1.order.dto.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Integer id;

    private String name;
    private String username;

    private List<OrderResponse> orders;

}
