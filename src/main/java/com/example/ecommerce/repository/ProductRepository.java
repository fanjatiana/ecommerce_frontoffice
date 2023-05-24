package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByNameProductContainingOrDescriptionProductContainingOrCategory_NameCategoryContaining(String nameKeyword, String descriptionKeyword, String categoryNameKeyword);

}
