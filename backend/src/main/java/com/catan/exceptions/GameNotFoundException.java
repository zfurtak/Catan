package com.catan.exceptions;

public class GameNotFoundException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "404";

    public GameNotFoundException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
