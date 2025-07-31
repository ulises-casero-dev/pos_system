package com.ulises.possystem.controllers;

import com.ulises.possystem.dto.UserDTO;
import com.ulises.possystem.services.UserServiceManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceManager serviceManager;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return  ResponseEntity.ok(this.serviceManager.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(this.serviceManager.findById(id));
    }

    @PostMapping()
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO user){
        UserDTO userDto = this.serviceManager.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody UserDTO user){
        UserDTO userDto = this.serviceManager.update(id, user);
        return ResponseEntity.ok(userDto);
    }
}
