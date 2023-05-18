package com.catan.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "409";

    public UserAlreadyExistsException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
