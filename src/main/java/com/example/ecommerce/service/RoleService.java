package com.example.ecommerce.service;


import com.example.ecommerce.entity.Role;
import com.example.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Optional<Role> findById(int i) {
        return roleRepository.findById(i);
    }
}
