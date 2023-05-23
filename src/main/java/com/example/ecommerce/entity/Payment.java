package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Table(name="payments")
@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPayment;
    @NonNull
    private Date datePayment;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiry;
    private String cardCVV;

    @ManyToOne
    private Order order;

}