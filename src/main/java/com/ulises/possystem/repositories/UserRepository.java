package com.ulises.possystem.repositories;

import com.ulises.possystem.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
