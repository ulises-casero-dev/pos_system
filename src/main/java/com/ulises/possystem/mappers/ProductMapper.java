package com.ulises.possystem.mappers;

import com.ulises.possystem.dto.ProductCreateDTO;
import com.ulises.possystem.dto.ProductDTO;
import com.ulises.possystem.entities.Category;
import com.ulises.possystem.entities.Product;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        modelMapper.typeMap(Product.class, ProductDTO.class).addMappings(mapper -> {
                    //mapper.map(Product::getId, ProductDTO::setId);
                    mapper.map(src -> {
                        if (src.getCategory() != null) {
                            return src.getCategory().getName();
                        }
                        return null;
                    }, ProductDTO::setCategoryName);
                }
        );
    }

    public ProductDTO toDto(Product product) {
        return this.modelMapper.map(product, ProductDTO.class);
    }

    public Product toEntity(ProductCreateDTO productCreateDto) {
        Product product = modelMapper.map(productCreateDto, Product.class);

        Category category = new Category();
        category.setId(productCreateDto.getCategoryId());
        product.setCategory(category);

        return  product;
    }

    public Product toEntity(ProductDTO productDto) {
        return this.modelMapper.map(productDto, Product.class);
    }
}
