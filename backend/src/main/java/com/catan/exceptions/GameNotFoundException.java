package com.catan.exceptions;

/**
 * Thrown to indicate that a method has tried to retrieve from the database a Game that is not saved in the database.
 */
public class GameNotFoundException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "404";

    /**
     * Constructs a {@code GameNotFoundException} with the
     * specified detail message.
     * @param message the detail message
     */
    public GameNotFoundException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
