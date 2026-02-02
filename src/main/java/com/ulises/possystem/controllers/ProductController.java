package com.ulises.possystem.controllers;

import com.ulises.possystem.dto.product.ProductCreateDTO;
import com.ulises.possystem.dto.product.ProductDTO;
import com.ulises.possystem.dto.product.ProductUpdateDTO;
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

    @GetMapping("/actives")
    public ResponseEntity<List<ProductDTO>> findByActiveTrue() {
        return ResponseEntity.ok(this.serviceManager.findByActiveTrue());
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

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id,
                                             @Valid @RequestBody ProductUpdateDTO product) {
        ProductDTO productDto = this.serviceManager.update(id, product);
        return ResponseEntity.ok(productDto);
    }

    @PatchMapping("activate/{id}")
    public ResponseEntity<ProductDTO> activate(@PathVariable Long id) {
        ProductDTO activatedProduct = this.serviceManager.activate(id);
        return  ResponseEntity.ok(activatedProduct);
    }

    @PatchMapping("deactivate/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable Long id){
        ProductDTO deletedProduct = this.serviceManager.deactivate(id);
        return  ResponseEntity.ok(deletedProduct);
    }
}
