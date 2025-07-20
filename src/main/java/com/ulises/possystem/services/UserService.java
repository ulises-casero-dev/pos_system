package com.ulises.possystem.services;

import com.ulises.possystem.dto.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO save(UserDTO userDto);
    UserDTO update(Long id, UserDTO userDto);
}
