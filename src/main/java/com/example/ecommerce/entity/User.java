package com.example.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class User implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    @NonNull
    @Pattern(regexp = "^[A-Za-z]+(?:-[A-Za-z]+)?(?: [A-Za-z]+(?:-[A-Za-z]+)?)?$", message = "Prénom d'utilisateur non valide (autorisés : lettres,espace, caractères spéciaux (-))")
    private String username;

    @NonNull
    private String password;

    @Pattern(regexp = "^[A-Za-z]+(?:-[A-Za-z]+)?(?: [A-Za-z]+(?:-[A-Za-z]+)?)?$", message = "Nom d'utilisateur non valide (autorisés : lettres,espace, caractères spéciaux (-))")
    private String fullName;

    @NonNull
    private String email;

    @Pattern(regexp = "^[A-Za-z0-9\\s'.-]+$", message = "adresse non valide (autorisés : lettres, nombres, caractères spéciaux(' . -))")
    private String address;

    @Pattern(regexp = "^(\\+[0-9]{1,3})?[- .]?\\(?[0-9]{1,4}\\)?[- .]?[0-9]{1,4}[- .]?[0-9]{1,9}$", message = "Numéro de téléphone non valide (autorisés : nombres)")
    private String phoneNumber;

    @ManyToOne
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getRoleName()));
    }


    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
