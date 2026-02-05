package com.ulises.selfcheckout.services;

import com.ulises.selfcheckout.dto.user.UserCreateDTO;
import com.ulises.selfcheckout.dto.user.UserDTO;
import com.ulises.selfcheckout.dto.user.UserLoginDTO;
import com.ulises.selfcheckout.dto.user.UserUpdateDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    List<UserDTO> findAllActiveUsers();
    List<UserDTO> findAllDeactiveUsers();
    UserDTO findById(Long id);
    UserLoginDTO login(String memberIdentification);
    UserDTO save(UserCreateDTO userDto);
    UserDTO update(Long id, UserUpdateDTO userDto);
    void deactivate(Long id);
    void activate(Long id);
}
