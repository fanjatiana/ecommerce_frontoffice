package com.example.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;
    private String nameProduct;
    private String descriptionProduct;
    private double PriceProduct;
    private boolean selectedProduct;
    private String photoProduct;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(int idProduct, String nameProduct, String descriptionProduct, double priceProduct, boolean selectedProduct, String photoProduct, Category category) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        PriceProduct = priceProduct;
        this.selectedProduct = selectedProduct;
        this.photoProduct = photoProduct;
        this.category = category;
    }

    public Product(String nameProduct, String descriptionProduct, double priceProduct, boolean selectedProduct, String photoProduct, Category category) {
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        PriceProduct = priceProduct;
        this.selectedProduct = selectedProduct;
        this.photoProduct = photoProduct;
        this.category = category;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public double getPriceProduct() {
        return PriceProduct;
    }

    public boolean isSelectedProduct() {
        return selectedProduct;
    }

    public String getPhotoProduct() {
        return photoProduct;
    }

    public Category getCategory() {
        return category;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}