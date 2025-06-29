package com.oswaldo.proyecto1.user.model;

import com.oswaldo.proyecto1.order.model.OrderModel;
import com.oswaldo.proyecto1.product.model.ProductModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderModel> orders = new ArrayList<>();

}
