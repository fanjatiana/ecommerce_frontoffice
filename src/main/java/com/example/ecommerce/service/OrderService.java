package com.example.ecommerce.service;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    public void updateOrder(Order order, double totalAmount, Payment payment, List<OrderItem> orderItems, HttpSession session) {
        order.setTotalAmount(totalAmount);
        order.setPayment(payment);
        User user = (User) session.getAttribute("user");
        order.setUser(user);
        order.setOrderItems(orderItems);
        saveOrder(order);
        session.removeAttribute("orderItems");
    }
    public double calculateTotalAmount(List<OrderItem> orderItems) {
        double totalAmount = 0.0;
        for (OrderItem orderItem : orderItems) {
            totalAmount += orderItem.getProduct().getPriceProduct() * orderItem.getQuantity();
        }
        return totalAmount;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }
}