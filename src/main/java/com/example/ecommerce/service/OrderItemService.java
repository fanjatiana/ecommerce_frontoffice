package com.example.ecommerce.service;
import com.example.ecommerce.entity.OrderItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderItemService {

    public List<OrderItem> getCartItemsFromSession(HttpSession session) {
        return (List<OrderItem>) session.getAttribute("orderItems");
    }

    public double calculateTotalPrice(List<OrderItem> orderItems) {
        double total = 0.0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getProduct().getPriceProduct() * orderItem.getQuantity();
        }
        return total;
    }

    public void removeFromCart(HttpSession session, int productId) {
        List<OrderItem> orderItems = getCartItemsFromSession(session);
        if (orderItems != null) {
            orderItems.removeIf(item -> item.getProduct().getIdProduct() == productId);
            session.setAttribute("orderItems", orderItems);
        }
    }

    public void updateCartItemQuantity(HttpSession session, int productId, int quantity) {
        List<OrderItem> orderItems = getCartItemsFromSession(session);
        if (orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getProduct().getIdProduct() == productId) {
                    orderItem.setQuantity(quantity);
                    break;
                }
            }
        }
    }
}
