package com.ulises.possystem.services;

import com.ulises.possystem.dto.ProductDTO;
import com.ulises.possystem.entities.Product;
import com.ulises.possystem.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceManager implements ProductService{
    @Autowired // Inyecta una instancia de la clase, es un atributo mas de la clase, se accede con this.
    private ProductRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = repository.findAll();
        return products.stream()
                .map(product -> this.modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));;
        return this.modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO Save(ProductDTO productDto) {
        Product productEntity = this.modelMapper.map(productDto, Product.class);
        Product saveProduct = this.repository.save(productEntity);
        return this.modelMapper.map(saveProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO Update(Long id, ProductDTO productDto) {
        Product product = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found."));


        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());


       Product saveProduct = this.repository.save(product);
       return this.modelMapper.map(saveProduct, ProductDTO.class);
    }
}
