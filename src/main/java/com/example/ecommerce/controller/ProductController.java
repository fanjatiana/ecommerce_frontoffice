package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.OrderItemService;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/products")
    public String getAllProducts(Model model, Authentication authentication) {
        List<Product> products = productService.getAllProducts();

        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);

        if (products != null && itemQuantity > -1) {
            model.addAttribute("products", products);
            List<Category> categoryNames = categoryService.getAllCategory();
            model.addAttribute("categoryNames", categoryNames);
            model.addAttribute("productsQuantity", itemQuantity);
            return "products";
        } else {
            return "redirect:/404";
        }

    }

    @GetMapping("/products/cat/{categoryName}")
    public String getProductsByCategory(@PathVariable("categoryName") String categoryName, Model model, Authentication authentication) {
        List<Product> products = productService.getProductsByCategory(categoryName);
        List<Category> categoryNames = categoryService.getAllCategory();

        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);

        if (products != null && categoryNames != null && itemQuantity > -1) {
            model.addAttribute("categoryNames", categoryNames);
            model.addAttribute("products", products);
            model.addAttribute("productsQuantity", itemQuantity);
            return "products";
        } else {
            return "redirect:/404";
        }


    }


    /* display ramdom category

    @GetMapping("/home")
            public String getCategorys(Model model){
            List<Category> categorys = categoryService.fetchRandomCategorys(5);
            model.addAttribute("categorys", categorys);
            return "home";
    }*/

    /* display category list
    @GetMapping("/home")
    public String getCategorys(Model model){
        List<Category> categorys = categoryService.fetchRandomCategorys(5);
        model.addAttribute("categorys", categorys);
        return "home";
    }*/


    @PostMapping("/products/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model, Authentication authentication) {
        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);
        List<Product> products = productService.searchProducts(keyword);

        if (products != null && itemQuantity > -1) {
            model.addAttribute("productsQuantity", itemQuantity);
            model.addAttribute("products", products);
            return "products";
        } else {
            return "redirect:/404";
        }


    }
}
