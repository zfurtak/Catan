package com.catan.exceptions;

/**
 * Thrown to indicate that a method has tried to save a player in the database that is already saved in there.
 * @author Zuzanna Furtak
 * @author rorro6787
 */
public class PlayerAlreadyExistsException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "409";

    /**
     * Constructs a {@code PlayerAlreadyExistsException} with the
     * specified detail message.
     * @param message the detail message
     */
    public PlayerAlreadyExistsException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
