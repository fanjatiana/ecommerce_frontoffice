package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.OrderItemService;
import com.example.ecommerce.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/order-details/{orderId}")
    public String getOrderDetails(@PathVariable int orderId, Model model, Authentication authentication) {
        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);
        Optional<Order> orderOptional = orderService.getOrderById(orderId);
        if (orderOptional.isPresent() && itemQuantity>-1) {
            Order order = orderOptional.get();
            List<Category> categoryNames = categoryService.getAllCategory();
            if (categoryNames != null) {
                model.addAttribute("categoryNames", categoryNames);
                model.addAttribute("order", order);
                model.addAttribute("productsQuantity", itemQuantity);
                return "order-details";
            }
        }
        return "redirect:/404";
    }

    @GetMapping("/my-orders")
    public String getAllMyOrders(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);
        List<Order> orderList = orderRepository.findOrderByUser(user);
        List<Category> categoryNames = categoryService.getAllCategory();
        if (authentication.isAuthenticated() && orderList != null && categoryNames != null  && itemQuantity>-1) {
                model.addAttribute("categoryNames", categoryNames);
                model.addAttribute("orders", orderList);
            model.addAttribute("productsQuantity", itemQuantity);
                return "my-orders";
            } else {
                return "redirect:/404";
            }
        }



}