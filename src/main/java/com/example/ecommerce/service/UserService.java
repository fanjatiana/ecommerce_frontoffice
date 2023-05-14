package com.example.ecommerce.service;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User AddUser() {
        User user1 = new User("Pierre", "petitchat44", "Bateau", "pierreB@Email.com", "36 rue du chateau 44000 Nantes", "0240257895");
        return userRepository.save(user1);
    }

}
