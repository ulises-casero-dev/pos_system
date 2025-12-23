package com.ulises.possystem.controllers;

import com.ulises.possystem.dto.ApiMessage;
import com.ulises.possystem.dto.category.CategoryDTO;
import com.ulises.possystem.services.CategoryServiceManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/actives")
    public ResponseEntity<List<CategoryDTO>> findActives(){
        return ResponseEntity.ok(this.serviceManager.findByActiveTrue());
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

    @PutMapping("/activate/{id}")
    public ResponseEntity<ApiMessage> activateCategory(@PathVariable Long id) {
        this.serviceManager.activate(id);
        return ResponseEntity.ok(new ApiMessage("Category activation **successful**"));
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<ApiMessage> deactivateCategory(@PathVariable Long id){
        this.serviceManager.deactivate(id);
        return ResponseEntity.ok(new ApiMessage("Category deactivation **successful**"));
    }
}
