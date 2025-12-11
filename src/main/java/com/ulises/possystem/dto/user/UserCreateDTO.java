package com.ulises.possystem.dto.user;

import com.ulises.possystem.enums.UserType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class UserCreateDTO {

    @NotNull(message = "The name can't be null.")
    private String name;

    @NotNull(message = "The surname can't be null.")
    private String surname;

    @NotNull(message = "The email can't be null.")
    @Email(message = "PLease provide a vaild email address.")
    private String email;


    private String phone;

    @NotNull(message = "The identification can't be null.")
    private String identification;

    @NotNull(message = "The userType can't be null.")
    private UserType userType;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getIdentification() {
        return identification;
    }

    public UserType getUserType() {
        return userType;
    }
}
