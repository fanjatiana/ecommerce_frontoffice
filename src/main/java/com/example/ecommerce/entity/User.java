package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    @NonNull
    private String username;
    @NonNull
    private String password;
    private String fullName;
    @NonNull
    private String email;
    private String address;
    private String phoneNumber;
    @ManyToOne
    private Role role;

}
