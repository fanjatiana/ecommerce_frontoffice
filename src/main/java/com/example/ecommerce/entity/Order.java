package com.example.ecommerce.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder;
    private Date dateOrder;
    private double totalAmount;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @OneToOne(mappedBy = "order")
    private Payment payment;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
