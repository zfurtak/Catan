package com.catan.exceptions;

/**
 * Thrown to indicate that a player is trying to trade a resource in exchange of the saem resource.
 * @author rorro6787
 */
public class BadTradingException extends RuntimeException {
    private static final String AUTH_EXCEPTION_ERROR_CODE = "405";

    /**
    * Constructs a {@code BadTradingException} with the
    * specified detail message.
    * @param message the detail message
    */
    public BadTradingException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
