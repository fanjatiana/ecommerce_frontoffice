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
public class OrderItemService {
    @Autowired
    private ProductRepository productRepository;
    public List<OrderItem> getCartItemsFromAuthentication(Authentication authentication) {
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
            return getOrderItemsFromAuthentication(auth);
        }
        return new ArrayList<>();
    }

    public double calculateTotalPrice(List<OrderItem> orderItems) {
        double total = 0.0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getProduct().getPriceProduct() * orderItem.getQuantity();
        }
        return total;
    }

    public void addToCart(Authentication authentication, int productId, int quantity) {
        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.get();
        OrderItem orderItem = new OrderItem(product, quantity);

        List<OrderItem> orderItems = getCartItemsFromAuthentication(authentication);
        orderItems.add(orderItem);

        Authentication updatedAuthentication = createUpdatedAuthentication(authentication, orderItems);
        SecurityContextHolder.getContext().setAuthentication(updatedAuthentication);
    }

    public void removeFromCart(Authentication authentication, int productId) {
        List<OrderItem> orderItems = getCartItemsFromAuthentication(authentication);
        if (orderItems != null) {
            orderItems.removeIf(item -> item.getProduct().getIdProduct() == productId);
            Authentication updatedAuthentication = createUpdatedAuthentication(authentication, orderItems);
            SecurityContextHolder.getContext().setAuthentication(updatedAuthentication);
        }
    }

    public void updateCartItemQuantity(Authentication authentication, int productId, int quantity) {
        List<OrderItem> orderItems = getCartItemsFromAuthentication(authentication);
        if (orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getProduct().getIdProduct() == productId) {
                    orderItem.setQuantity(quantity);
                    break;
                }
            }
            Authentication updatedAuthentication = createUpdatedAuthentication(authentication, orderItems);
            SecurityContextHolder.getContext().setAuthentication(updatedAuthentication);
        }
    }

    private List<OrderItem> getOrderItemsFromAuthentication(UsernamePasswordAuthenticationToken auth) {
        if (auth.getDetails() instanceof List) {
            return (List<OrderItem>) auth.getDetails();
        }
        return new ArrayList<>();
    }

    private Authentication createUpdatedAuthentication(Authentication authentication, List<OrderItem> orderItems) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
        auth.setDetails(orderItems);
        return auth;
    }

    public int calculateTotalQuantity(List<OrderItem> orderItems) {
        int totalQuantity = 0;
        for (OrderItem orderItem : orderItems) {
            totalQuantity += orderItem.getQuantity();
        }
        return totalQuantity;
    }
}
