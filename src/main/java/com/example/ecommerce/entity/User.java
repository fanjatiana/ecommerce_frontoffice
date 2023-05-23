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
    @NonNull
    private String fullName;
    @NonNull
    private String email;
    @NonNull
    private String address;
    @NonNull
    private String phoneNumber;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

}
