package com.ulises.selfcheckout.controllers;

import com.ulises.selfcheckout.dto.LoginRequest;
import com.ulises.selfcheckout.dto.user.UserCreateDTO;
import com.ulises.selfcheckout.dto.user.UserDTO;
import com.ulises.selfcheckout.dto.user.UserLoginDTO;
import com.ulises.selfcheckout.dto.user.UserUpdateDTO;
import com.ulises.selfcheckout.services.UserServiceManager;
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

    @PostMapping("/login")
    public  ResponseEntity<UserLoginDTO> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(this.serviceManager.login(request.getMemberIdentification()));
    }

    @GetMapping("/active")
    public ResponseEntity<List<UserDTO>> getAllActiveUsers(){
        return  ResponseEntity.ok(this.serviceManager.findAllActiveUsers());
    }

    @GetMapping("/deactive")
    public ResponseEntity<List<UserDTO>> findAllDeactiveUsers(){
        return  ResponseEntity.ok(this.serviceManager.findAllDeactiveUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(this.serviceManager.findById(id));
    }

    @PostMapping()
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserCreateDTO user){
        UserDTO userDto = this.serviceManager.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody UserUpdateDTO user){
        UserDTO userDto = this.serviceManager.update(id, user);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("deactivate/{id}")
    public ResponseEntity<String> deactivateUser(@PathVariable Long id) {
        this.serviceManager.deactivate(id);
        return ResponseEntity.ok("User deactivated.");
    }

    @PatchMapping("activate/{id}")
    public ResponseEntity<String> activateUser(@PathVariable Long id) {
        this.serviceManager.activate(id);
        return ResponseEntity.ok("User activated.");
    }
}
