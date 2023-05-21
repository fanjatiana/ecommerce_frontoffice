package com.example.ecommerce.controller;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/payment")
    public String showPaymentTunnel(Model model) {
        PaymentForm paymentForm = new PaymentForm();
        paymentForm.setOrder(new Order());
        paymentForm.setPayment(new Payment());
        model.addAttribute("paymentForm", paymentForm);
        return "payment";
    }

    @PostMapping("/payment")
    public String makePayment(@ModelAttribute("paymentForm") PaymentForm paymentForm,
                              HttpSession session) {
        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");
        double totalAmount = orderService.calculateTotalAmount(orderItems);
        Order order = paymentForm.getOrder();
        Payment payment = paymentForm.getPayment();
        payment.setDatePayment(new Date());
        paymentService.savePayment(payment);
        orderService.updateOrder(order, totalAmount, payment, orderItems, session);

        return "redirect:/order/" + order.getIdOrder();
    }

}

