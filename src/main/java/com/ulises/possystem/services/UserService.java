package com.ulises.possystem.services;

import com.ulises.possystem.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    User update(Long id, User user);
}
