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
    @Size(min = 8, max = 8, message = "El campo debe tener exactamente 8 caracteres")
    private String identification;

    @NotNull(message = "The userType can't be null.")
    private UserType userType;

    public UserCreateDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
