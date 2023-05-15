package com.catan.exceptions;

public class PlayerAlreadyExistsExceptions extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "409";

    public PlayerAlreadyExistsExceptions(String message) {
        new PlayerAlreadyExistsExceptions(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }

    public PlayerAlreadyExistsExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
