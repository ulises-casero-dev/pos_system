package com.ulises.postsystem.services;

import com.ulises.postsystem.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id); // Seria ideal utilizar Optional
    Product Save(Product product);
}
