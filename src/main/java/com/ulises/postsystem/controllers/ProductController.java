package com.ulises.postsystem.controllers;

import com.ulises.postsystem.entities.Product;
import com.ulises.postsystem.services.ProductServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductServiceManager serviceManager;

    @PostMapping()
    @Transactional
    public Product save(@RequestBody Product product){
        return  this.serviceManager.Save(product);
    }

    @GetMapping()
    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return this.serviceManager.findAll();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public Product getByIdProduct(@PathVariable Long id){
        return this.serviceManager.findById(id);
    }
}
