package com.catan.exceptions;

public class PasswordIncorrectException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "401";

    public PasswordIncorrectException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
