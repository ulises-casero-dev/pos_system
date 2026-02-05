package com.ulises.selfcheckout.exception.business;

public class CategoryInUseException extends RuntimeException {
    public CategoryInUseException(Long categoryId){
        super("Cannot deactivate category with ID " + categoryId
                + " because it is associated with one or more products.");
    }
}
