package com.tvd12.ezydata.example.jpa.exception;

public class BookNotFoundException extends NotFoundException {

    public BookNotFoundException(String msg) {
        super(msg);
    }
}
