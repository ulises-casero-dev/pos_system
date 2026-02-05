package com.ulises.selfcheckout.exception.business;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String name){
        super("A product with name " + name + " already exists.");
    }
}
