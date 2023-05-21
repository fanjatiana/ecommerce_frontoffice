package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.RoleService;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PaymentController {

    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;
    @GetMapping("/payment")
    public String showPaymentPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            List<Product> productList = productService.getAllProducts();
            model.addAttribute("products", productList);

            double totalPrice = productService.calculateTotalPrice(productList);
            model.addAttribute("totalPrice", totalPrice);

            int totalProductSelected= productService.calculateProductQuantity();
            model.addAttribute("totalProducts", totalProductSelected);

            return "payment";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/payment-validated")
    public String showPaymentValidatedPage() {
        return "payment-validated";
    }
    @PostMapping("/payment")
    public String payTheOrder() {
        return "redirect:/payment-validated";
    }


    @GetMapping("/edit-delivery-address")
    public String showEditDeliveryAddressForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "edit-delivery-address";
        } else {
            model.addAttribute("errorMessage", "User not found");
            return "payment";
        }
    }


}
