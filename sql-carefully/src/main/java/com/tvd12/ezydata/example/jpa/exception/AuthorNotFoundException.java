package com.tvd12.ezydata.example.jpa.exception;

public class AuthorNotFoundException extends NotFoundException {

    public AuthorNotFoundException(String msg) {
        super(msg);
    }
}
