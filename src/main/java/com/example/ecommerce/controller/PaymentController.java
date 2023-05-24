package com.example.ecommerce.controller;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.service.*;
import jakarta.servlet.http.HttpSession;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PaymentController {

    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    RoleService roleService;
   /* @GetMapping("/payment")
    public String showPaymentPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            List<Product> productList = productService.getAllProducts();
            model.addAttribute("products", productList);

            double totalPrice = productService.calculateTotalPrice(productList);
            model.addAttribute("totalPrice", totalPrice);

            int totalProductSelected= productService.calculateProductQuantity();
            model.addAttribute("totalProducts", totalProductSelected);

            return "payment";
        } else {
            return "redirect:/login";
        }
    }*/

    @GetMapping("/payment")
    public String showPaymentTunnel(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        User user1 = userService.findByUsername(user.getUsername());
        List<OrderItem> orderItems = orderItemService.getCartItemsFromSession(session);
        double total = orderItemService.calculateTotalPrice(orderItems);

        int itemQuantity = orderItemService.calculateTotalQuantity(orderItems);

        if (user1 != null && orderItems != null) {
            model.addAttribute("user", user1);
            model.addAttribute("totalPrice", total);
            model.addAttribute("itemQuantity", itemQuantity);
            PaymentForm paymentForm = new PaymentForm();
            paymentForm.setOrder(new Order());
            paymentForm.setPayment(new Payment());
            model.addAttribute("paymentForm", paymentForm);
            return "payment";
        }else {
            return "redirect:/login";
        }
    }



    @GetMapping("/payment-validated")
    public String showPaymentValidatedPage() {
        return "payment-validated";
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
        return "redirect:/order-details/" + order.getIdOrder();
    }



  /*  @PostMapping("/payment")
    public String payTheOrder() {
        return "redirect:/payment-validated";
    }*/


    @GetMapping("/edit-delivery-address")
    public String showEditDeliveryAddressForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            userService.save(user);
            model.addAttribute("user", user);
            return "edit-delivery-address";
        } else {
            model.addAttribute("errorMessage", "User not found");
            return "payment";
        }
    }

    @PostMapping("/edit-delivery-address")
    public String editDeliveryAdress(@ModelAttribute("user") User user, Model model,HttpSession session){
        Optional<Role> clientRole = roleService.findById(1); // id 3 = User role
        Role role = clientRole.orElseThrow(() -> new IllegalStateException("User role not found"));
        user.setRole(role);
        userService.save(user);
        model.addAttribute("user", user);
            return "redirect:/payment"; // Redirect to a confirmation page or any other desired page
        }



}
