package com.catan.model.exceptions;

public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(String errorMessage){
        super(errorMessage);
    }
}
