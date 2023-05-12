package com.tvd12.ezydata.example.jpa.controller;

import com.tvd12.ezydata.example.jpa.converter.DataToResponseConverter;
import com.tvd12.ezydata.example.jpa.converter.RequestToDataConverter;
import com.tvd12.ezydata.example.jpa.data.AddCategoryData;
import com.tvd12.ezydata.example.jpa.data.CategoryData;
import com.tvd12.ezydata.example.jpa.request.AddCategoryRequest;
import com.tvd12.ezydata.example.jpa.response.CategoryResponse;
import com.tvd12.ezydata.example.jpa.service.CategoryService;
import com.tvd12.ezydata.example.jpa.validator.CategoryValidator;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller("/api/v1/category")
public class CategoryController {
    private final CategoryValidator categoryValidator;
    private final CategoryService categoryService;
    private final RequestToDataConverter requestToDataConverter;
    private final DataToResponseConverter dataToResponseConverter;

    @DoPost("/add")
    public CategoryResponse addCategory(@RequestBody AddCategoryRequest request) {
        categoryValidator.validate(request);
        final AddCategoryData addCategoryData = requestToDataConverter.toData(request);
        final CategoryData categoryData = categoryService.saveCategory(addCategoryData);
        return dataToResponseConverter.toResponse(categoryData);
    }

    @DoGet("/{categoryId}")
    public CategoryResponse getCategory(@PathVariable long categoryId) {
        final CategoryData categoryData = categoryService.getCategory(categoryId);
        return dataToResponseConverter.toResponse(categoryData);
    }
}
