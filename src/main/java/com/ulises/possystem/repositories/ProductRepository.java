package com.ulises.possystem.repositories;

import com.ulises.possystem.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
} // Esta interface generara las consultas sql necesarias para el manejo de datos, si quisiera crear consultas personalizadas debo hacerlo dentro de las llaves {}
