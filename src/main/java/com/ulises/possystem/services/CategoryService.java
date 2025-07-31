package com.ulises.possystem.services;

import com.ulises.possystem.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(Long id);
    CategoryDTO save(CategoryDTO categorySto);
    CategoryDTO update(Long id, CategoryDTO categoryDto);
    CategoryDTO deactivate(Long id);
    CategoryDTO activate(Long id);
}
