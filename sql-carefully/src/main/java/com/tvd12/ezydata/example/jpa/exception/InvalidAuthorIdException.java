package com.tvd12.ezydata.example.jpa.exception;

public class InvalidAuthorIdException extends IllegalArgumentException {
    public InvalidAuthorIdException(String msg) {
        super(msg);
    }
}
