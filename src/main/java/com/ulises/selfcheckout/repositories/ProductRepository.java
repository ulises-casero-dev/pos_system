package com.ulises.selfcheckout.repositories;

import com.ulises.selfcheckout.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(value = "Product.category")
    List<Product> findAll();

    List<Product> findByActiveTrue();

    boolean existsByName(String name);

    boolean existsByIdAndActiveFalse(Long id);

    @Query("SELECT Count(p) FROM Product p WHERE p.category.id = :categoryId")
    int countByCategoryId(@Param("categoryId") Long categoryId);


}
