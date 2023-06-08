package com.catan.exceptions;

/**
 * Thrown to indicate that a method has tried to build without fulfilling the necessary conditions of the building.
 * @author Zuzanna Furtak
 * @author rorro6787
 */
public class BuildingUnavailableException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "403";

    /**
    * Constructs a {@code BuildingUnavailableException} with the
    * specified detail message.
    * @param message the detail message
    */
    public BuildingUnavailableException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
