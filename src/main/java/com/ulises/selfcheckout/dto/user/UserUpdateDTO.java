package com.ulises.selfcheckout.dto.user;

import jakarta.validation.constraints.Email;

public class UserUpdateDTO {
    private String name;
    private String surname;

    @Email(message = "PLease provide a valid email address.")
    private String email;
    private String phone;

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

}
