package com.example.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;
    private String roleName;

    public Role(int idRole, String roleName) {
        this.idRole = idRole;
        this.roleName = roleName;
    }

    public Role() {
    }

    public int getIdRole() {
        return idRole;
    }

    public String getRoleName() {
        return roleName;
    }
}
