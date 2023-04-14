package com.catan.model.exceptions;

public class PlayerNotFoundException extends RuntimeException{

    public PlayerNotFoundException(String errorMessage){
        super(errorMessage);
    }

}
