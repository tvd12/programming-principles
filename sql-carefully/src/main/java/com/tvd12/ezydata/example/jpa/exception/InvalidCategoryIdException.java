package com.tvd12.ezydata.example.jpa.exception;

public class InvalidCategoryIdException extends IllegalArgumentException {
    public InvalidCategoryIdException(String msg) {
        super(msg);
    }
}
