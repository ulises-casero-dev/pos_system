package com.ulises.possystem.services;

import com.ulises.possystem.dto.product.ProductCreateDTO;
import com.ulises.possystem.dto.product.ProductDTO;
import com.ulises.possystem.dto.product.ProductUpdateDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO save(ProductCreateDTO productCreateDto);
    ProductDTO update(Long id, ProductUpdateDTO productDto);
    ProductDTO deactivate(Long id);
    ProductDTO activate(Long id);
}
