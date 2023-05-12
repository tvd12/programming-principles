package com.tvd12.ezydata.example.jpa.exception;

public class DuplicatedCategoryException extends IllegalArgumentException {
    public DuplicatedCategoryException(String msg) {
        super(msg);
    }
}
