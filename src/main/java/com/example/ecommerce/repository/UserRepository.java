package com.example.ecommerce.repository;

import com.example.ecommerce.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);


    List<User> findByRole_IdRole(int roleId);

}