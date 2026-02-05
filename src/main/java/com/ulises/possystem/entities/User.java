package com.ulises.possystem.entities;

import com.ulises.possystem.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String memberIdentification;

    @Column
    private String celphone;

    @Column
    @Email
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column
    private boolean active;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User() {}

    public User(Long id, String name, String surname, String memberIdentification, String celphone, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.memberIdentification = memberIdentification;
        this.celphone = celphone;
        this.email = email;
    }

    @PrePersist
    public void active(){
        this.active = true;
    }
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

    public String getCelphone() {
        return celphone;
    }

    public void setCelphone(String celphone) {
        this.celphone = celphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
