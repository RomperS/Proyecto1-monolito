package com.oswaldo.proyecto1.order.model;

import com.oswaldo.proyecto1.user.model.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailsModel> orderDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    private double totalPrice;

    private LocalDateTime date;

    private String address;

}
