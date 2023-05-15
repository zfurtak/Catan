package com.catan.exceptions;

public class BadTradingException extends RuntimeException {
    private static final String AUTH_EXCEPTION_ERROR_CODE = "405";

    public BadTradingException(String message) {
        new BadTradingException(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }

    public BadTradingException(String message, Throwable cause) {
        super(message, cause);
    }
}
