package com.ulises.possystem.services;

import com.ulises.possystem.dto.ProductDTO;
import com.ulises.possystem.entities.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO Save(ProductDTO productDto);
    ProductDTO Update(Long id, ProductDTO productDto);
}
