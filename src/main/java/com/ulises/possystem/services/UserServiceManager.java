package com.ulises.possystem.services;

import com.ulises.possystem.dto.UserDTO;
import com.ulises.possystem.entities.User;
import com.ulises.possystem.exception.ResourceNotFoundException;
import com.ulises.possystem.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Fallback;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceManager implements UserService{
    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> findAll(){
        List<User> users = this.repository.findAll();

        return users.stream()
                .map(user -> this.modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id){
        User user = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO save(UserDTO userDto){
        User user = this.modelMapper.map(userDto, User.class);
        User savedUser = this.repository.save(user);

        return this.modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO update(Long id, UserDTO userDto) {
        User userDB = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        userDB.setName(userDto.getName());
        userDB.setSurname(userDto.getSurname());
        userDB.setCelphone(userDto.getCelphone());
        userDB.setEmail(userDto.getEmail());

        User updatedUser = repository.save(userDB);
        return  modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public UserDTO deactivate(Long id) {
        User userEntity = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        userEntity.setActive(false);

        User userUpdated = this.repository.save(userEntity);

        return this.modelMapper.map(userUpdated, UserDTO.class);
    }

    @Override
    public UserDTO activate(Long id) {
        User userEntity = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        userEntity.setActive(true);

        User userUpdated = this.repository.save(userEntity);

        return this.modelMapper.map(userUpdated, UserDTO.class);
    }
}
