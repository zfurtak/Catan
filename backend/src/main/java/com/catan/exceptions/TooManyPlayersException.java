package com.catan.exceptions;

/**
 * Thrown to indicate that a method tries to save a new Player when the Game already has four players.
 */
public class TooManyPlayersException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "403";

    /**
     * Constructs a {@code TooManyPlayersException} with the
     * specified detail message.
     * @param message the detail message
     */
    public TooManyPlayersException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
