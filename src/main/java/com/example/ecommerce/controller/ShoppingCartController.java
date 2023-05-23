package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    ProductService productService;

    /*@GetMapping("/shopping-cart")
    public String displayShoppingCartPage(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("products", productList);

        double totalPrice = productService.calculateTotalPrice(productList);
        model.addAttribute("totalPrice", totalPrice);

        int totalProductSelected= productService.calculateProductQuantity();
        model.addAttribute("totalProducts", totalProductSelected);

        return "shopping-cart";
    }*/

    // calcule du nombre de produit total (produit selectionnée + quantité => ajout d'un attribut quantité dans products ?)
   /* @PostMapping("/shopping-cart")
    public String addQuantity(Model model, @RequestParam("quantity") String quantity){
        int quantitySelected = Integer.parseInt(quantity);
        int totalProductSelected = productService.calculateProductQuantity(quantitySelected);
        model.addAttribute("totalProductSelected", totalProductSelected);
        return "shopping-cart";
    }*/
}
