package com.catan.exceptions;

public class TooManyPlayersException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "403";

    public TooManyPlayersException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
