package com.ulises.possystem.services;

import com.ulises.possystem.dto.category.CategoryCreateDTO;
import com.ulises.possystem.dto.category.CategoryDTO;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
    List<CategoryDTO> findByActiveTrue();
    CategoryDTO findById(Long id);
    CategoryDTO save(CategoryCreateDTO categorySto);
    CategoryDTO update(Long id, CategoryDTO categoryDto);
    void deactivate(Long id);
    void activate(Long id);
}
