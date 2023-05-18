package com.catan.exceptions;

public class UserNotFoundException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "404";

    public UserNotFoundException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }

}
