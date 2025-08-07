package com.ulises.possystem.services;

import com.ulises.possystem.dto.user.UserCreateDTO;
import com.ulises.possystem.dto.user.UserDTO;
import com.ulises.possystem.dto.user.UserUpdateDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    List<UserDTO> findAllActiveUsers();
    List<UserDTO> findAllDeactiveUsers();
    UserDTO findById(Long id);
    UserDTO save(UserCreateDTO userDto);
    UserDTO update(Long id, UserUpdateDTO userDto);
    void deactivate(Long id);
    void activate(Long id);
}
