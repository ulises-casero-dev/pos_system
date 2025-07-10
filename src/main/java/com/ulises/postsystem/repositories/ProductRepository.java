package com.ulises.postsystem.repositories;

import com.ulises.postsystem.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
} // Esta interface generara las consultas sql necesarias para el manejo de datos, si quisiera crear consultas personalizadas debo hacerlo dentro de las llaves {}
