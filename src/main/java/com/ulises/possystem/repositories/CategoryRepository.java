package com.ulises.possystem.repositories;

import com.ulises.possystem.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByActiveTrue();

    boolean existsByName(String name);

    boolean existsByIdAndActiveTrue(Long id);

    @Modifying
    @Query("UPDATE Category category SET category.active = :active WHERE category.id = :id")
    void updateActivateStatus(@Param("id") Long id, @Param("active") boolean active);

}
