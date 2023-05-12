package com.tvd12.ezydata.example.jpa.exception;

public class DuplicatedBookException extends IllegalArgumentException {
    public DuplicatedBookException(String msg) {
        super(msg);
    }
}
