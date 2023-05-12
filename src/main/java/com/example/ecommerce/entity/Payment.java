package com.example.ecommerce.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name="payements")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPayment;
    private Date datePayment;
    @ManyToOne
    @JoinColumn(name = "idOrder")
    private Order order;
}
