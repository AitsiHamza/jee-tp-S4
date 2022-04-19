package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
    Page<Product> getByNameContains(String name,Pageable pageable);
}
