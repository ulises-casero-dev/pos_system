package com.ulises.possystem.services;

import com.ulises.possystem.entities.Category;

import java.util.List;
public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category save(Category category);
    Category update(Long id, Category category);
}
