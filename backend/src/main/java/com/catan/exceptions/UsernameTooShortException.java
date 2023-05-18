package com.catan.exceptions;

public class UsernameTooShortException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "400";

    public UsernameTooShortException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}