package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="roles")
@Getter
@Setter
@NoArgsConstructor(force = true)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;
    @NonNull
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
