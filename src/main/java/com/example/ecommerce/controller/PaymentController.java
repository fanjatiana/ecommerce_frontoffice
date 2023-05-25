package com.example.ecommerce.controller;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.service.OrderItemService;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/payment")
    public String showPaymentTunnel(Model model, Authentication authentication) {
        PaymentForm paymentForm = new PaymentForm();
        paymentForm.setOrder(new Order());
        paymentForm.setPayment(new Payment());
        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        double totalAmount = orderService.calculateTotalAmount(orderItems);
        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);
        model.addAttribute("totalPrice", totalAmount);
        model.addAttribute("itemQuantity", itemQuantity);
        model.addAttribute("paymentForm", paymentForm);
        return "payment";
    }

    @PostMapping("/payment")
    public String makePayment(@ModelAttribute("paymentForm") PaymentForm paymentForm, Authentication authentication) {
        List<OrderItem> orderItems = orderItemService.getCartItemsFromAuthentication(authentication);
        double totalAmount = orderService.calculateTotalAmount(orderItems);
        Order order = paymentForm.getOrder();
        Payment payment = paymentForm.getPayment();
        payment.setDatePayment(new Date());
        paymentService.savePayment(payment);
        orderService.updateOrder(order, totalAmount, payment, orderItems, authentication);

        return "redirect:/order/" + order.getIdOrder();
    }

}

