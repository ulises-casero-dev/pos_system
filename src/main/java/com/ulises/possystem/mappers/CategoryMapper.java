package com.ulises.possystem.mappers;

import com.ulises.possystem.dto.category.CategoryDTO;
import com.ulises.possystem.entities.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


public class CategoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDTO toDto(Category category) {
        return this.modelMapper.map(category, CategoryDTO.class);
    }

    public Category toEntity(CategoryDTO categoryDto){
        return this.modelMapper.map(categoryDto, Category.class);

    }
}
