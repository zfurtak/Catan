package com.catan.exceptions;

/**
 * Thrown to indicate that a method has tried to save a user in the database with a username that has less than four letters.
 * @author Minerva Gomez
 */
public class UsernameTooShortException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "400";

    /**
     * Constructs an {@code UsernameTooShortException} with the
     * specified detail message.
     * @param message the detail message
     * @author Minerva Gomez
     */
    public UsernameTooShortException(String message) {
        new UsernameTooShortException(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }

    public UsernameTooShortException(String message, Throwable cause) {
        super(message, cause);
    }
}