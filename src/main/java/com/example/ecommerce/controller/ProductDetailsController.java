package com.example.ecommerce.controller;

import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/products/{productId}")
    public String getProductDetails(@PathVariable int productId, Model model) {
        Optional<Product> productOptional = productService.getProductById(productId);
        Product product = productOptional.get();
        model.addAttribute("product", product);
        return "product-details";
    }

    @PostMapping("/products/{productId}/addToCart")
    public String addToCart(@PathVariable int productId, @RequestParam int quantity, HttpSession session) {
        productService.addToCart(productId, quantity, session);
        return "redirect:/products/{productId}";
    }

}
