package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.OrderItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/cart")
    public String getCartItems(Model model, Authentication authentication) {
        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        if (orderItems.isEmpty()) {
            return "redirect:/account";
        }
        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);
        double total = orderItemService.calculateTotalPrice(orderItems);
        List<Category> categoryNames = categoryService.getAllCategory();
        model.addAttribute("categoryNames", categoryNames);
        model.addAttribute("totalPrice", total);
        model.addAttribute("itemQuantity", itemQuantity);
        model.addAttribute("orderItems", orderItems);
        return "cart";
    }

    @PostMapping("/cart/{productId}/remove")
    public String removeFromCart(@PathVariable int productId, Authentication authentication) {
        orderItemService.removeFromCart(authentication, productId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/{productId}/updateQuantity")
    public String updateCartItemQuantity(@PathVariable int productId, @RequestParam int quantity, Authentication authentication) {
        orderItemService.updateCartItemQuantity(authentication, productId, quantity);
        return "redirect:/cart";
    }
}



