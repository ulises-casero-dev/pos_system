package com.ulises.possystem.exception.business;

public class InvalidDiscountScopeException extends RuntimeException {
    public InvalidDiscountScopeException(){
        super("A discount applied to a product must be of type GENERAL.");
    }
}
