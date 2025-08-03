package com.ulises.possystem.services;

import com.ulises.possystem.dto.product.ProductCreateDTO;
import com.ulises.possystem.dto.product.ProductDTO;
import com.ulises.possystem.dto.product.ProductUpdateDTO;
import com.ulises.possystem.entities.Category;
import com.ulises.possystem.entities.Product;
import com.ulises.possystem.exception.ResourceNotFoundException;
import com.ulises.possystem.repositories.CategoryRepository;
import com.ulises.possystem.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceManager implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> this.modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));;
        return this.modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO save(ProductCreateDTO productCreateDto) {
        Product productEntity = this.modelMapper.map(productCreateDto, Product.class);

        Category category = this.categoryRepository.findById(productCreateDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        productEntity.setCategory(category);

        Product saveProduct = this.productRepository.save(productEntity);

        return this.modelMapper.map(saveProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO update(Long id, ProductUpdateDTO productDto) {
        Product productEntity = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));


        productEntity.setName(productDto.getName());
        productEntity.setPrice(productDto.getPrice());

        if (productDto.getCategoryId() != null) {
            Category category = this.categoryRepository.findById(productDto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

            productEntity.setCategory(category);
        }


       Product saveProduct = this.productRepository.save(productEntity);
       return this.modelMapper.map(saveProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO deactivate(Long id) {
        Product productEntity = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

        productEntity.setActive(false);

        Product productUpdated = this.productRepository.save(productEntity);

        return this.modelMapper.map(productUpdated, ProductDTO.class);
    }

    @Override
    public ProductDTO activate(Long id) {
        Product productEntity = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

        productEntity.setActive(true);

        Product productUpdated = this.productRepository.save(productEntity);

        return this.modelMapper.map(productUpdated, ProductDTO.class);
    }
}
