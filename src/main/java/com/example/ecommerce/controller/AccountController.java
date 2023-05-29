package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/account")
    public String showAccount(Model model, Authentication authentication) {
        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);
        List<Category> categoryNames = categoryService.getAllCategory();
        if(categoryNames != null && itemQuantity > -1){
            model.addAttribute("categoryNames", categoryNames);
            model.addAttribute("productsQuantity", itemQuantity);
            return "account";
        } else {
            return "redirect:/404";
        }
    }
}
