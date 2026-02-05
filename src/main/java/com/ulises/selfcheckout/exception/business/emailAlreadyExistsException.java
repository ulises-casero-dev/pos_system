package com.ulises.selfcheckout.exception.business;

public class emailAlreadyExistsException extends RuntimeException {
    public emailAlreadyExistsException(String email) {
        super("The email " + email + " already exists.");
    }
}
