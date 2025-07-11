package com.ulises.possystem.repositories;

import com.ulises.possystem.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
