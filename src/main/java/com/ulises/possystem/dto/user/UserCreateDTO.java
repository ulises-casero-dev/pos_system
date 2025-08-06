package com.ulises.possystem.dto.user;

import com.ulises.possystem.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class UserCreateDTO {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String identification;
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
