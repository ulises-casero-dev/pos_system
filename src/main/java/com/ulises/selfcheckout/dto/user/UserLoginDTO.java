package com.ulises.selfcheckout.dto.user;

import com.ulises.selfcheckout.enums.UserType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserLoginDTO {
    private Long id;
    private String name;
    private String surname;
    private String memberIdentification;
    private UserType type;

    public UserLoginDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMemberIdentification() {
        return memberIdentification;
    }

    public void setMemberIdentification(String memberIdentification) {
        this.memberIdentification = memberIdentification;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
