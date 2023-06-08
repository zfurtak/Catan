package com.catan.exceptions;

/**
 * Thrown to indicate that a method has tried to retrieve from the database a User that is not saved in the database.
 * @author rorro6787
 * @author Zuzanna Furtak
 */
public class UserNotFoundException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "404";

    /**
     * Constructs an {@code UserNotFoundException} with the
     * specified detail message.
     * @param message the detail message
     */
    public UserNotFoundException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }

}
