package com.example.ecommerce.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPayment;
    private Date datePayment;

    @ManyToOne
    private Order order;

}
