package com.ulises.selfcheckout.exception.business;

public class UserInactiveException extends RuntimeException{
    public UserInactiveException (String memberIdentification) {
        super("The user with identification \"" + memberIdentification + "\" cannot be used because it is currently deactivated.");
    }
}
