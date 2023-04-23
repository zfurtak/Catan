package com.catan.exceptions;

public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(String errorMessage){
        super(errorMessage);
    }
}
