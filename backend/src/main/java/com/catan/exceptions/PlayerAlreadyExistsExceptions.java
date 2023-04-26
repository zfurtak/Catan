package com.catan.exceptions;

public class PlayerAlreadyExistsExceptions extends RuntimeException{
    public PlayerAlreadyExistsExceptions(String errorMessage){
        super(errorMessage);
    }
}
