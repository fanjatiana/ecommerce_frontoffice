package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.OrderItemService;
import com.example.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductDetailsController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products/{productId}")
    public String getProductDetails(@PathVariable int productId, Model model) {
        Optional<Product> productOptional = productService.getProductById(productId);
        Product product = productOptional.get();
        List<Category> categoryNames = categoryService.getAllCategory();
        if (productOptional.isPresent() && categoryNames != null) {
            model.addAttribute("categoryNames", categoryNames);
            model.addAttribute("product", product);
            return "product-details";
        } else {
            return "redirect:/404";
        }

    }

    @PostMapping("/products/{productId}/addToCart")
    public String addToCart(@PathVariable int productId, @RequestParam int quantity, Authentication authentication) {
        if (authentication != null) {
            orderItemService.addToCart(authentication, productId, quantity);
            return "redirect:/products/{productId}";
        } else {
            return "redirect:/login";
        }

    }
}

