package com.ulises.possystem.exception.business;

public class CategoryInactiveException extends RuntimeException{
    public CategoryInactiveException(Long id){
        super("The category with ID \"" + id + "\" cannot be used because it is currently deactivated.");
    }
}
