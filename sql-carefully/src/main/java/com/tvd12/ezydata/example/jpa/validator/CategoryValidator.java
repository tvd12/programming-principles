package com.tvd12.ezydata.example.jpa.validator;

import com.tvd12.ezydata.example.jpa.request.AddCategoryRequest;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;

@EzySingleton
public class CategoryValidator {

    private static final int MAX_NAME_LENGTH = 50;

    public void validate(AddCategoryRequest request) {
        if (request.getCategoryName().length() > MAX_NAME_LENGTH) {
            throw new HttpBadRequestException("name length must < " + MAX_NAME_LENGTH);
        }
    }
}
