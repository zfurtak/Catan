package com.catan.exceptions;


public class BadResourceNumberException extends RuntimeException {
    private static final String AUTH_EXCEPTION_ERROR_CODE = "404";

    public BadResourceNumberException(String message) {
        new BadResourceNumberException(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }

    public BadResourceNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
