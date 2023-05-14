package com.example.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;
    @ManyToOne
    private Role role;

    public User() {
    }

    public User(int idUser, String username, String password, String fullName, String email, String address, String phoneNumber, Role role) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public User(int idUser, String username, Role role) {
        this.idUser = idUser;
        this.username = username;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }
}
