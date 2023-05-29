package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.OrderItemService;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/profile")
    public String showProfile(Model model, Authentication authentication) {

        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);
        if (authentication != null && authentication.isAuthenticated() && itemQuantity > -1) {
            User user = (User) authentication.getPrincipal();
            List<Category> categoryNames = categoryService.getAllCategory();
            model.addAttribute("categoryNames", categoryNames);
            model.addAttribute("productsQuantity", itemQuantity);
            model.addAttribute("user", user);
            return "profile";
        } else {
            return "redirect:/404";
        }
    }
}
