package com.ulises.possystem.services;

import com.ulises.possystem.dto.ProductCreateDTO;
import com.ulises.possystem.dto.ProductDTO;
import com.ulises.possystem.entities.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO save(ProductCreateDTO productCreateDto);
    ProductDTO update(Long id, ProductDTO productDto);
    ProductDTO deactivate(Long id);
    ProductDTO activate(Long id);
}
