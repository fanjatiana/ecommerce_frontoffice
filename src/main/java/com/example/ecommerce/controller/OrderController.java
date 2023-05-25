package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.OrderService;
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

    @GetMapping("/order-details/{orderId}")
    public String getOrderDetails(@PathVariable int orderId, Model model) {
        Optional<Order> orderOptional = orderService.getOrderById(orderId);
        Order order = orderOptional.get();
        List<Category> categoryNames = categoryService.getAllCategory();
        model.addAttribute("categoryNames", categoryNames);
        model.addAttribute("order", order);
        return "order-details";
    }

    @GetMapping("/my-orders")
    public String getAllMyOrders(Model model){
        List<Order> orderList = orderService.getAllOrders();
        List<Category> categoryNames = categoryService.getAllCategory();
        model.addAttribute("categoryNames", categoryNames);
        model.addAttribute("orders",orderList);
        return "my-orders";
    }
}