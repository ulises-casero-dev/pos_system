package com.ulises.possystem.exception.business;

public class ProductInctiveException extends RuntimeException {
    public ProductInctiveException(Long id){
        super("The product with ID \"" + id + "\" cannot be used because it is currently deactivated.");
    }
}
