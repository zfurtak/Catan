package com.catan.exceptions;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException(String errorMessage){
        super(errorMessage);
    }

}
