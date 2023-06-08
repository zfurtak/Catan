package com.catan.exceptions;

/**
 * Thrown to indicate that a method has tried to save a user in the databse that is already saved in there.
 * @author rorro6787
 */
public class UserAlreadyExistsException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "409";

    /**
     * Constructs an {@code UserAlreadyExistsException} with the
     * specified detail message.
     * @param message the detail message
     */
    public UserAlreadyExistsException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
