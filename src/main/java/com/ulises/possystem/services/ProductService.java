package com.ulises.possystem.services;

import com.ulises.possystem.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id); // Seria ideal utilizar Optional
    Product Save(Product product);
    Product Update(Long id, Product product);
}
