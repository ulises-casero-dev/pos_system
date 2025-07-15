package com.ulises.possystem.controllers;

import com.ulises.possystem.entities.Category;
import com.ulises.possystem.services.CategoryServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceManager serviceManager;

    @GetMapping()
    @Transactional(readOnly = true)
    public List<Category> findAllCategories(){
        return  this.serviceManager.findAll();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public Category getCategoryById(@PathVariable Long id){
        return this.serviceManager.findById(id);
    }

    @PostMapping()
    @Transactional
    public Category saveCategory(@RequestBody Category category){
        return this.serviceManager.save(category);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateCategory(@PathVariable Long id,
                                            @RequestBody Category category){
        Optional<Category> categoryData = Optional.of(this.serviceManager.findById(id));

        if(categoryData.isPresent()){
            Category categoryToUpdate = categoryData.get();
            categoryToUpdate.setName(category.getName());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.serviceManager.update(id,categoryToUpdate));
        }
        return ResponseEntity.notFound().build();
    }
}
