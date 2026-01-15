package com.ulises.possystem.exception.business;

public class ProductInactiveException extends RuntimeException {
    public ProductInactiveException(Long id){
        super("The product with ID \"" + id + "\" cannot be used because it is currently deactivated.");
    }
}
