package com.catan.exceptions;

public class GameNotFoundException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "404";

    public GameNotFoundException(String message) {
        new GameNotFoundException(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }

    public GameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
