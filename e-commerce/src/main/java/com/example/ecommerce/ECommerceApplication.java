package com.example.ecommerce;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            Stream.of("Computer","Printer","SmartPhone").forEach(
                    name -> {
                        productRepository.save(new Product(UUID.randomUUID().toString(),name,Math.random()*100,Math.random()*100)
                        );
                    }
            );
        };
    }
}
