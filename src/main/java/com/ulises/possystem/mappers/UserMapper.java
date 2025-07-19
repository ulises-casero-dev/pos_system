package com.ulises.possystem.mappers;

import com.ulises.possystem.dto.UserDTO;
import com.ulises.possystem.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toDto(User user) {
        return  modelMapper.map(user, UserDTO.class);
    }

    public User toEntity(UserDTO userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
