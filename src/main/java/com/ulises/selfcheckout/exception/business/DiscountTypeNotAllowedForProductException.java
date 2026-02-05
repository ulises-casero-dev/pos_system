package com.ulises.selfcheckout.exception.business;

public class DiscountTypeNotAllowedForProductException extends RuntimeException {
    public DiscountTypeNotAllowedForProductException(){
        super("EMPLOYEE or CUSTOMER discounts cannot be applied to a product.");
    }
}
