package com.ulises.selfcheckout.mappers;

import com.ulises.selfcheckout.dto.user.UserDTO;
import com.ulises.selfcheckout.entities.User;
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
