package com.example.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name="orderitems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrderItem;

    @ManyToOne
    private Product product;
    private int quantity;
    private double price;

    @ManyToOne
    private Order order;
}
