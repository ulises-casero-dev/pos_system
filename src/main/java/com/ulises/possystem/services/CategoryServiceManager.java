package com.ulises.possystem.services;

import com.ulises.possystem.entities.Category;
import com.ulises.possystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CategoryServiceManager implements CategoryService{
    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return (List<Category>) this.repository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return this.repository.findById(id).get();
    }

    @Override
    public Category save(@RequestBody Category category) {
        return this.repository.save(category);
    }

    @Override
    public Category update(@RequestBody Long id, Category category) {
        Category categoryData = this.repository.findById(id).get();

        categoryData.setName(category.getName());

        return this.repository.save(categoryData);
    }
}
