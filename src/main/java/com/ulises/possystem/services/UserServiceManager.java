package com.ulises.possystem.services;

import com.ulises.possystem.entities.User;
import com.ulises.possystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserServiceManager implements UserService{
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll(){
        return this.repository.findAll();
    }

    @Override
    public User findById(Long id){
        return this.repository.findById(id).get();
    }

    @Override
    public User save(User user){
        return this.repository.save(user);
    }

    @Override
    public User update(Long id, User user){
        User user_data = this.repository.findById(id).get();

        user_data.setName(user.getName());
        user_data.setSurname(user.getSurname());
        user_data.setCelphone(user.getCelphone());
        user_data.setEmail(user.getEmail());

        return this.repository.save(user_data);
    }
}
