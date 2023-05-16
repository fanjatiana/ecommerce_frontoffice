package com.example.ecommerce.controller;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EditProfileController {
    @Autowired
    private UserRepository userRepository;

    // reste à utiliser userService au lieu de userRepository !
    @GetMapping("/edit-profile")
    public String showEditProfileForm(@RequestParam("id") int id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
        model.addAttribute("user", user);
        return "edit-profile";
    }

    @PostMapping("/edit-profile")
    public String updateProfile(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:profile";
    }
}
