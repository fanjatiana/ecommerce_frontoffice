package com.example.ecommerce.controller;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.RoleService;
import com.example.ecommerce.service.UserDetailsServiceImpl;
import com.example.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    
    @GetMapping("/login")
    public String formLogin(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        Optional<Role> clientRole = roleService.findById(3);
       /* Optional<Role> admin = roleService.findById(2);
        Optional<Role> superAdmin = roleService.findById(1);*/

        user.setRole(clientRole.orElse(null));
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (bindingResult.hasErrors()) {
            return "signup";
        }
        userService.save(user);
        return "redirect:/login";
    }

}
