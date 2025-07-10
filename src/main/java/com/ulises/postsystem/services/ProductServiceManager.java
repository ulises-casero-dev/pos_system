package com.ulises.postsystem.services;

import com.ulises.postsystem.entities.Product;
import com.ulises.postsystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductServiceManager implements ProductService{
    @Autowired // Inyecta una instancia de la clase, es un atributo mas de la clase, se accede con this.
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) this.repository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.repository.findById(id).get();
    }

    @Override
    public Product Save(@RequestBody Product product) {
        return this.repository.save(product);
    }
}
