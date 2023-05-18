package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.RoleService;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class EditProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/edit-profile")
    public String showEditProfileForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "edit-profile";
        } else {
            model.addAttribute("errorMessage", "User not found");
            return "profile";
        }
    }
    @PostMapping("/edit-profile")
    public String updateProfile(@ModelAttribute("user") User user, Model model) {
        Optional<Role> clientRole = roleService.findById(3);
        user.setRole(clientRole.orElse(null));
        userService.save(user);
        model.addAttribute("user", user);
        return "redirect:/profile";
    }
}
