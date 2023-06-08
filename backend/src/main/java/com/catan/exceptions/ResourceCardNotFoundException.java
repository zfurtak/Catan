package com.catan.exceptions;

/**
 * Thrown to indicate that a method has tried to retireve from the database a PlayerResourceCard that is not saved
 * in the database.
 * @author Minerva Gomez
 */
public class ResourceCardNotFoundException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "404";

    /**
     * Constructs a {@code ResourceCardNotFoundException} with the
     * specified detail message.
     * @param message the detail message
     */
    public ResourceCardNotFoundException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }

}
