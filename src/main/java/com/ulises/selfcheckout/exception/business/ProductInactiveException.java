package com.ulises.selfcheckout.exception.business;

public class ProductInactiveException extends RuntimeException {
    public ProductInactiveException(Long id){
        super("The product with ID \"" + id + "\" cannot be used because it is currently deactivated.");
    }
}
