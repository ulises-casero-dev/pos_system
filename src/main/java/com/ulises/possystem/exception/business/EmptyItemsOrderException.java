package com.ulises.possystem.exception.business;

public class EmptyItemsOrderException extends RuntimeException {
    public EmptyItemsOrderException(){
        super("The items list cannot be empty");
    }
}
