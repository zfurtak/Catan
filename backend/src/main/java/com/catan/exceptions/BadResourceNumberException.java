package com.catan.exceptions;

/**
 * Thrown to indicate that a method is referencing a Resource with a number bigger than 5.
 * @author rorro6787
 */
public class BadResourceNumberException extends RuntimeException {
    private static final String AUTH_EXCEPTION_ERROR_CODE = "404";

    /**
    * Constructs a {@code UserNotFoundException} with the
    * specified detail message.
    * @param message the detail message
    */
    public BadResourceNumberException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
