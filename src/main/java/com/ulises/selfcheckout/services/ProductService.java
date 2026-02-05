package com.ulises.selfcheckout.services;

import com.ulises.selfcheckout.dto.product.ProductCreateDTO;
import com.ulises.selfcheckout.dto.product.ProductDTO;
import com.ulises.selfcheckout.dto.product.ProductUpdateDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    List<ProductDTO> findByActiveTrue();
    ProductDTO findById(Long id);
    ProductDTO save(ProductCreateDTO productCreateDto);
    ProductDTO update(Long id, ProductUpdateDTO productDto);
    ProductDTO deactivate(Long id);
    ProductDTO activate(Long id);
}
