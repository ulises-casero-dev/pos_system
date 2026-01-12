package com.ulises.possystem.services;

import com.ulises.possystem.dto.category.CategoryCreateDTO;
import com.ulises.possystem.dto.category.CategoryDTO;
import com.ulises.possystem.entities.Category;
import com.ulises.possystem.exception.BadRequestException;
import com.ulises.possystem.exception.CategoryAlreadyExistsException;
import com.ulises.possystem.exception.CategoryInUseException;
import com.ulises.possystem.exception.ResourceNotFoundException;
import com.ulises.possystem.repositories.CategoryRepository;
import com.ulises.possystem.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceManager implements CategoryService{
    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = this.repository.findAll();
        return categories.stream()
                .map(category -> this.modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findByActiveTrue(){
         List<Category> categories = this.repository.findByActiveTrue();
        return categories.stream().
                map(category -> this.modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
        Category categoryEntity = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        return this.modelMapper.map(categoryEntity, CategoryDTO.class);
    }

    @Override
    public CategoryDTO save(CategoryCreateDTO categoryDto) {

        if (this.repository.existsByName(categoryDto.getName())) {
            throw new CategoryAlreadyExistsException(categoryDto.getName());
        } else {
            Category categoryEntity = this.modelMapper.map(categoryDto, Category.class);
            Category savedCategory = this.repository.save(categoryEntity);

            return this.modelMapper.map(savedCategory, CategoryDTO.class);
        }
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDto) {
        Category categoryEntity = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        categoryEntity.setName(categoryDto.getName());

        Category updatedCategory = this.repository.save(categoryEntity);

        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }

    @Transactional
    @Override
    public void deactivate(Long id) {
        if(this.productRepository.countByCategoryId(id) > 0) {
            throw new CategoryInUseException(id);
        }
        else {
            this.repository.updateActivateStatus(id, false);
        }
    }

    @Transactional
    @Override
    public void activate(Long id) {this.repository.updateActivateStatus(id, true);}
}
