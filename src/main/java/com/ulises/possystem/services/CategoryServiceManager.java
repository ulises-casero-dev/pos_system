package com.ulises.possystem.services;

import com.ulises.possystem.dto.CategoryDTO;
import com.ulises.possystem.entities.Category;
import com.ulises.possystem.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceManager implements CategoryService{
    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = this.repository.findAll()
        return categories.stream()
                .map(category -> this.modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
        Category categoryEntity = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return this.modelMapper.map(categoryEntity, CategoryDTO.class);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDto) {
        Category categoryEntity = this.modelMapper.map(categoryDto, Category.class);
        Category savedCategory = this.repository.save(categoryEntity);

        return this.modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDto) {
        Category categoryEntity = this.repository.findById(id)
                .orElseThrow();

        categoryEntity.setName(categoryDto.getName());

        Category updatedCategory = this.repository.save(categoryEntity);

        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }
}
