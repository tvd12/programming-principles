package com.tvd12.ezydata.example.jpa.validator;

import com.tvd12.ezydata.example.jpa.request.AddBookRequest;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;

@EzySingleton
public class BookValidator {

    private static final int MAX_NAME_LENGTH = 50;

    public void validate(AddBookRequest request) {
        if (request.getBookName().length() > MAX_NAME_LENGTH) {
            throw new HttpBadRequestException("name length must < " + MAX_NAME_LENGTH);
        }
    }
}
