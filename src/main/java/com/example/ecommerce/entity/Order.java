package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder;
    @NonNull
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private double totalAmount;
    private String shippingAddressLine1;
    private String shippingAddressLine2;
    private String shippingCity;
    private String shippingState;
    private String shippingPostalCode;

    @ManyToOne
    private User user;

    @OneToOne
    private Payment payment;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
