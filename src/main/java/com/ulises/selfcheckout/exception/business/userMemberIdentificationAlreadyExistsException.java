package com.ulises.selfcheckout.exception.business;

public class userMemberIdentificationAlreadyExistsException extends RuntimeException {
    public userMemberIdentificationAlreadyExistsException(String memberIdentification) {
        super("The member identification " + memberIdentification + " already exists.");
    }
}
