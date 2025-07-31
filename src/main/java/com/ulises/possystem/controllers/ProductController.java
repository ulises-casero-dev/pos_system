package com.ulises.possystem.controllers;

import com.ulises.possystem.dto.ProductCreateDTO;
import com.ulises.possystem.dto.ProductDTO;
import com.ulises.possystem.services.ProductServiceManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductServiceManager serviceManager;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(this.serviceManager.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getByIdProduct(@PathVariable Long id){
        return ResponseEntity.ok(this.serviceManager.findById(id));
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> saveProduct(@Valid  @RequestBody ProductCreateDTO product){
        ProductDTO productDto = this.serviceManager.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> Update(@PathVariable Long id,
                                             @RequestBody ProductDTO product) {
        ProductDTO productDto = this.serviceManager.update(id, product);
        return ResponseEntity.ok(productDto);
    }
}
