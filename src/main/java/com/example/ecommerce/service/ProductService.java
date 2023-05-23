package com.example.ecommerce.service;

import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }
    public void addToCart(int productId, int quantity, HttpSession session) {
        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.get();
        OrderItem orderItem = new OrderItem(product, quantity);
        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        orderItems.add(orderItem);
        session.setAttribute("orderItems", orderItems);
    }

   /* public double calculateTotalPrice(List<Product> productList){
        double totalPrice = 0.0;
        for (Product product : productList) {
            if (product.isSelectedProduct()) {
                totalPrice += product.getPriceProduct();
            }
        }
        return totalPrice;
    }

    public int calculateProductQuantity(){
        List<Product> productList = productRepository.findAll();
        int productCount =0;
        for (Product product : productList) {
            if (product.isSelectedProduct()) {
                productCount++;
            }
        }
        return productCount;
    }

  /* fonction permettant le calcule des quantité de produits selectionnés

   public int calculateProductQuantity(int quantity){
        System.out.println(quantity);
        List<Product> productList = productRepository.findAll();
        int productQuantity = quantity;
        for (Product product : productList) {
            if (product.isSelectedProduct()) {
                productQuantity+= product.getQuantity;
            }
        }
        return productQuantity;
    }*/
}
