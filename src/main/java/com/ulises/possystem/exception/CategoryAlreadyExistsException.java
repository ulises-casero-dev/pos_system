package com.ulises.possystem.exception;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException(String name){
        super("A category with name " + name + " already exists.");
    }
}
