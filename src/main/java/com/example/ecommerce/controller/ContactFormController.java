package com.example.ecommerce.controller;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class ContactFormController {

    @Autowired
    UserRepository userRepository;
    @GetMapping("/contact-form")
    public String showIdProfile(Model model) {
            int id = 1;
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                model.addAttribute("user", user);
                return "contact-form";
            } else {
                return "redirect:account";
            }
        }


}
