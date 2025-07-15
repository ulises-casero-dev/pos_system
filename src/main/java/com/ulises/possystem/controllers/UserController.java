package com.ulises.possystem.controllers;

import com.ulises.possystem.entities.Category;
import com.ulises.possystem.entities.User;
import com.ulises.possystem.services.CategoryServiceManager;
import com.ulises.possystem.services.UserServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceManager serviceManager;

    @GetMapping()
    @Transactional(readOnly = true)
    public List<User> findAllUsers(){
        return  this.serviceManager.findAll();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public User getUserById(@PathVariable Long id){
        return this.serviceManager.findById(id);
    }

    @PostMapping()
    @Transactional
    public User saveUser(@RequestBody User user){
        return this.serviceManager.save(user);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                            @RequestBody User user){
        Optional<User> userData = Optional.of(this.serviceManager.findById(id));

        if(userData.isPresent()){
            User userToUpdate = userData.get();
            userToUpdate.setName(user.getName());
            userToUpdate.setSurname(user.getSurname());
            userToUpdate.setCelphone(user.getCelphone());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.serviceManager.update(id,userToUpdate));
        }
        return ResponseEntity.notFound().build();
    }
}
