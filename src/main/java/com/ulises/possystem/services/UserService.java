package com.ulises.possystem.services;

import com.ulises.possystem.dto.user.UserCreateDTO;
import com.ulises.possystem.dto.user.UserDTO;
import com.ulises.possystem.dto.user.UserUpdateDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO save(UserCreateDTO userDto);
    UserDTO update(Long id, UserUpdateDTO userDto);
    UserDTO deactivate(Long id);
    UserDTO activate(Long id);
}
