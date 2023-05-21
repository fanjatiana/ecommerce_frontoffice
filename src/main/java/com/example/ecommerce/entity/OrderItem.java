package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="orderitems")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrderItem;
    @NonNull
    @ManyToOne
    private Product product;
    @NonNull
    private int quantity;

    private double price;

    @ManyToOne
    private Order order;
}
