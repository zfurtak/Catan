package com.catan.exceptions;

public class BuildingUnavailableException extends RuntimeException{
    private static final String AUTH_EXCEPTION_ERROR_CODE = "403";

    public BuildingUnavailableException(String message) {
        super(message, new Throwable(AUTH_EXCEPTION_ERROR_CODE));
    }
}
