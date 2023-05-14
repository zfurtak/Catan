package com.catan.exceptions;

import lombok.Data;
@Data
public class BadResourceNumberException extends RuntimeException {

    public BadResourceNumberException(){
        super();
    }
}
