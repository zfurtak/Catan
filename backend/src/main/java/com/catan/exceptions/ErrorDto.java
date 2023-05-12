package com.catan.exceptions;

import lombok.Data;
@Data
public class ErrorDto {
    private String errorCode;
    private String message;
}
