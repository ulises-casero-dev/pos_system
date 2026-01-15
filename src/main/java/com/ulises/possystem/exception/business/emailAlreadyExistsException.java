package com.ulises.possystem.exception.business;

public class emailAlreadyExistsException extends RuntimeException {
    public emailAlreadyExistsException(String email) {
        super("The email " + email + " already exists.");
    }
}
