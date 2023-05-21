package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public double calculateTotalPrice(List<Product> productList){
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
