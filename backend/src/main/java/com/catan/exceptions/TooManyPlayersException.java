package com.catan.exceptions;

public class TooManyPlayersException extends RuntimeException{
    public TooManyPlayersException(String errorMessage){
        super(errorMessage);
    }
}
