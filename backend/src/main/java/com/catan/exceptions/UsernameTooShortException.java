package com.catan.exceptions;

public class UsernameTooShortException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "400";

    public UsernameTooShortException(String message) {
        new UsernameTooShortException(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }

    public UsernameTooShortException(String message, Throwable cause) {
        super(message, cause);
    }
}