package com.example.ecommerce.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class AccountController {
    @GetMapping("/account")
    public String showAccount() {
            return "account";
    }
}
