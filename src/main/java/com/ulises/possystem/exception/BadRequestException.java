package com.ulises.possystem.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String messaje){
        super(messaje);
    }
}
