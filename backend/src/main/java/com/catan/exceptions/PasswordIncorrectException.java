package com.catan.exceptions;

/**
 * Thrown to indicate that a Player has tried to log in with an incorrect password.
 */
public class PasswordIncorrectException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "401";
    
    /**
     * Constructs a {@code PasswordIncorrectException} with the
     * specified detail message.
     * @param message the detail message
     */
    public PasswordIncorrectException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
