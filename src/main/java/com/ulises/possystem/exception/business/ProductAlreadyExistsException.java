package com.ulises.possystem.exception.business;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String name){
        super("A product with name " + name + " already exists.");
    }
}
