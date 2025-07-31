package com.ulises.possystem.controllers;

import com.ulises.possystem.dto.CategoryDTO;
import com.ulises.possystem.services.CategoryServiceManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceManager serviceManager;

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> findAllCategories(){
        return  ResponseEntity.ok(this.serviceManager.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(this.serviceManager.findById(id));
    }

    @PostMapping()
    public ResponseEntity<CategoryDTO> saveCategory(@Valid @RequestBody CategoryDTO category){
        CategoryDTO categoryDto = this.serviceManager.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id,
                                            @Valid @RequestBody CategoryDTO category){
        CategoryDTO categoryDto = this.serviceManager.update(id, category);
        return ResponseEntity.ok(categoryDto);
    }
}
