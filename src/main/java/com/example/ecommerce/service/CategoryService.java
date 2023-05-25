package com.example.ecommerce.service;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }



    /*public List<Category> fetchRandomCategorys(int limit){
        List<Category> allCaregorys = categoryRepository.findAll();
        int totalCategorys = allCaregorys.size();

        if(totalCategorys>limit){
            Random random = new Random();

            List<Category> randomCategory = new ArrayList<>();

            while (randomCategory.size()< limit){
                int randomIndex = random.nextInt(totalCategorys);
                Category category = allCaregorys.get(randomIndex);

                if (!randomCategory.contains(category)) {
                    randomCategory.add(category);
                }
            }
            return randomCategory;
        }else {
            return allCaregorys;
        }
    }*/


}

