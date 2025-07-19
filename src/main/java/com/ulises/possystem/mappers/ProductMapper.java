package com.ulises.possystem.mappers;

import com.ulises.possystem.dto.ProductDTO;
import com.ulises.possystem.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO toDto(Product product) {
        return this.modelMapper.map(product, ProductDTO.class);
    }

    public Product toEntity(ProductDTO productDto) {
        return this.modelMapper.map(productDto, Product.class);
    }
}
