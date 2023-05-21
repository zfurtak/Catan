package com.catan.exceptions;

public class PlayerAlreadyExistsException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "409";

    public PlayerAlreadyExistsException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
