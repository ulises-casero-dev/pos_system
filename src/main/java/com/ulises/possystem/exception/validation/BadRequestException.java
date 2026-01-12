package com.ulises.possystem.exception.validation;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String messaje){
        super(messaje);
    }
}
