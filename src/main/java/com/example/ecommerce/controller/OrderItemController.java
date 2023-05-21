package com.example.ecommerce.controller;

import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.service.OrderItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/cart")
    public String getCartItems(Model model, HttpSession session) {
        List<OrderItem> orderItems = orderItemService.getCartItemsFromSession(session);
        if (orderItems == null) {
            return "redirect:/account";
        }
        double total = orderItemService.calculateTotalPrice(orderItems);
        model.addAttribute("totalPrice", total);
        model.addAttribute("orderItems", orderItems);
        return "cart";
    }

    @PostMapping("/cart/{productId}/remove")
    public String removeFromCart(@PathVariable int productId, HttpSession session) {
        orderItemService.removeFromCart(session, productId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/{productId}/updateQuantity")
    public String updateCartItemQuantity(@PathVariable int productId, @RequestParam int quantity, HttpSession session) {
        orderItemService.updateCartItemQuantity(session, productId, quantity);
        return "redirect:/cart";
    }
}

