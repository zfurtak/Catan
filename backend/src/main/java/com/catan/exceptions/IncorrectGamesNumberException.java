package com.catan.exceptions;

public class IncorrectGamesNumberException extends RuntimeException{

    private static final String AUTH_EXCEPTION_ERROR_CODE = "409";

    public IncorrectGamesNumberException(String message) {
        new IncorrectGamesNumberException(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }

    public IncorrectGamesNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
