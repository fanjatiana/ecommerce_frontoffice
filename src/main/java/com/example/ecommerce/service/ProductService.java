package com.example.ecommerce.service;

import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    private List<OrderItem> getOrderItemsFromAuthentication(UsernamePasswordAuthenticationToken auth) {
        if (auth.getDetails() instanceof List) {
            return (List<OrderItem>) auth.getDetails();
        }
        return new ArrayList<>();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameProductContainingOrDescriptionProductContainingOrCategory_NameCategoryContaining(keyword, keyword, keyword);
    }
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategory_NameCategory(categoryName);
    }



}
