package com.ulises.possystem.exception.business;

public class userMemberIdentificationAlreadyExistsException extends RuntimeException {
    public userMemberIdentificationAlreadyExistsException(String memberIdentification) {
        super("The member identification " + memberIdentification + " already exists.");
    }
}
