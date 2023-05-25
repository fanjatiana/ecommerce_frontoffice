package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        List<Category> categoryNames = categoryService.getAllCategory();
        model.addAttribute("categoryNames", categoryNames);
        return "products";
    }

    @GetMapping("/products/cat/{categoryName}")
    public String getProductsByCategory(@PathVariable("categoryName") String categoryName, Model model) {
        List<Product> products = productService.getProductsByCategory(categoryName);
        model.addAttribute("products", products);
        return "products";
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
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        return "products";
    }
}
