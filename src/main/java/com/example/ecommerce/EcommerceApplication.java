package com.example.ecommerce;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
@Autowired
    UserService userService;

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category("Basket","basket montante");
        Product product1 = new Product("Basket", "Envie d’une paire de chaussures idéale ? Ces baskets confortables et polyvalentes sont juste ce qu’il vous faut : parfaites pour le travail, les loisirs et tout le reste. ", 150, true,"basket.jpg",category1);
        productRepository.save(product1);

        Category category2 = new Category("Tennis","Tennis confortable");
        Product product2 = new Product("Tennis", "a course à pied n'a jamais été aussi belle. Ces chaussures de course pratiques sont une version inspirée des sentiers des chaussures de course Puma classiques ", 80, true,"Tennis.jpg",category2);
        productRepository.save(product2);

        Category category3 = new Category("Sneackers","Sneackers montante");
        Product product3 = new Product("Sneackers", "sNous nous engageons à vous fournir des chaussures de qualité qui vous permettront de montrer facilement vos points forts en toutes occasions.", 180, false,"snackers.jpg",category3);
        productRepository.save(product3);



        User user1 = new User("John","azerty","Doe", "jDoe@email.com", "3 rue du chat 44000 Nantes", "0254879652",new Role("USER"));
        userRepository.save(user1);
        User admin1 = new User("Pierre","qwerty","P", "pP@email.com", "10 rue du chat 44000 Nantes", "0754879652",new Role("ADMIN"));
        userRepository.save(admin1);
        User admin2 = new User("Julie","qwerty","E", "jE@email.com", "13 rue du chat 44000 Nantes", "0954879652",new Role("ADMIN"));
        userRepository.save(admin2);
        User admin3 = new User("Idris","qwerty","F", "iF@email.com", "33 rue du chat 44000 Nantes", "0154879652",new Role("ADMIN"));
        userRepository.save(admin3);

    }
}
