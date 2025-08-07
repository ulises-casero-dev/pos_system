package com.ulises.possystem.services;

import com.ulises.possystem.dto.user.UserCreateDTO;
import com.ulises.possystem.dto.user.UserDTO;
import com.ulises.possystem.dto.user.UserUpdateDTO;
import com.ulises.possystem.entities.User;
import com.ulises.possystem.exception.ResourceNotFoundException;
import com.ulises.possystem.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<UserDTO> findAllActiveUsers(){
        List<User> users = this.repository.findByActiveTrue();

        return users.stream()
                .map(user -> this.modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findAllDeactiveUsers(){
        List<User> users = this.repository.findByActiveFalse();

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
    public UserDTO save(UserCreateDTO userDto){
        User user = new User();

        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setCelphone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setMemberIdentification(userDto.getIdentification());
        user.setUserType(userDto.getUserType());

        User savedUser = this.repository.save(user);

        return this.modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO update(Long id, UserUpdateDTO userDto) {
        User userEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setCelphone(userDto.getPhone());

        User updatedUser = repository.save(userEntity);
        return  modelMapper.map(updatedUser, UserDTO.class);
    }

    @Transactional
    @Override
    public void deactivate(Long id) {
        this.repository.UpdateActiveStatus(id, false);
    }

    @Transactional
    @Override
    public void activate(Long id) {
        this.repository.UpdateActiveStatus(id, true);
    }
}
