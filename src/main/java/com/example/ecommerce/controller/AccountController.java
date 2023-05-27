package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/account")
    public String showAccount(Model model) {
        List<Category> categoryNames = categoryService.getAllCategory();
        if(categoryNames != null){
            model.addAttribute("categoryNames", categoryNames);
            return "account";
        } else {
            return "redirect:/404";
        }
    }
}
