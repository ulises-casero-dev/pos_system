package com.ulises.selfcheckout.mappers;

import com.ulises.selfcheckout.dto.category.CategoryDTO;
import com.ulises.selfcheckout.entities.Category;
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
