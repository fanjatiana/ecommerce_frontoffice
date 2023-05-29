package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ContactFormController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/contact-form")
    public String showIdProfile(Model model, Authentication authentication) {
        int id = 1;
        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);
        Optional<User> optionalUser = userRepository.findById(id);
        List<Category> categoryNames = categoryService.getAllCategory();
        model.addAttribute("categoryNames", categoryNames);
        if (optionalUser.isPresent() && itemQuantity > -1) {
            User user = optionalUser.get();
            model.addAttribute("productsQuantity", itemQuantity);
            model.addAttribute("user", user);
            return "contact-form";
        } else {
            return "redirect:/404";
        }
    }

    @GetMapping("/thanks")
    public String showThanksPage(Authentication authentication,Model model) {
        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);
        if(itemQuantity > -1){
            model.addAttribute("productsQuantity", itemQuantity);
        }

        return "thanks";
    }

    @PostMapping("/thanks")
    public String PostMessage() {
        return "thanks";
    }
}
