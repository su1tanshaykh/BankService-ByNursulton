package com.example.bankservise.exception;

public class BadRequest extends RuntimeException {
    public BadRequest(String message){
        super(message);
    }
}
