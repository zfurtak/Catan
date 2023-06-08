package com.catan.exceptions;

/**
 * Thrown to indicate that a method has received an incorrect number of games.
 * @author Zuzanna Furtak
 * @author rorro6787
 */
public class IncorrectGamesNumberException extends RuntimeException{

    private static final String AUTH_EXCEPTION_ERROR_CODE = "409";

    /**
     * Constructs an {@code IncorrectGamesNumberException} with the
     * specified detail message.
     * @param message the detail message
     */
    public IncorrectGamesNumberException(String message) {
        new IncorrectGamesNumberException(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
    /**
     * Constructs a new {@code IncorrectGamesNumberException} with the specified detail message and cause. 
     * Note that the detail message associated with cause is not automatically incorporated in 
     * this runtime exception's detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the getCause() method). 
     * (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public IncorrectGamesNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
