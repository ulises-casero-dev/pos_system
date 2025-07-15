package com.ulises.possystem.controllers;

import com.ulises.possystem.entities.Product;
import com.ulises.possystem.services.ProductServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductServiceManager serviceManager;

    @PostMapping()
    @Transactional
    public Product saveProduct(@RequestBody Product product){
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

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> Update(@PathVariable Long id,
                                    @RequestBody Product product) {
        Optional<Product> product1 = Optional.of(this.serviceManager.findById(id));

        if(product1.isPresent()){
            Product newProduct = product1.get();
            newProduct.setName(product.getName());
            newProduct.setPrice(product.getPrice());

            //this.serviceManager.Update(id, newProduct);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.serviceManager.Update(id, newProduct));
        }

        return ResponseEntity.notFound().build();
    }
}
