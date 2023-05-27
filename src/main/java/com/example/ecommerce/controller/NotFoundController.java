package com.example.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotFoundController {
    @GetMapping("/404")
    public String show404Page(){
        return "404";
    }
}
