package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.OrderItemService;
import com.example.ecommerce.service.RoleService;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EditProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/edit-profile")
    public String showEditProfileForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Category> categoryNames = categoryService.getAllCategory();
        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);
        model.addAttribute("categoryNames", categoryNames);
        if (authentication.isAuthenticated() && itemQuantity > -1) {
            model.addAttribute("productsQuantity", itemQuantity);
            User user = (User) authentication.getPrincipal();
            model.addAttribute("user", user);
            return "edit-profile";
        } else {
            return "redirect:/404";
        }
    }

    @PostMapping("/edit-profile")
    public String updateProfile(@ModelAttribute("user") User user, Model model) {
        Optional<Role> clientRole = roleService.findById(3);
        if (clientRole.isPresent()) {
            user.setRole(clientRole.orElse(null));
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.save(user);
            model.addAttribute("user", user);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
            return "redirect:/profile";
        } else {
            return "redirect:/404";
        }

    }
}
