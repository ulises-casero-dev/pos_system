package com.ulises.selfcheckout.exception.business;

public class InvalidDiscountScopeException extends RuntimeException {
    public InvalidDiscountScopeException(){
        super("General discounts must be associated with a product.");
    }
}
