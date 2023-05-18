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
    private User user;

    @OneToOne
    private Payment payment;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
