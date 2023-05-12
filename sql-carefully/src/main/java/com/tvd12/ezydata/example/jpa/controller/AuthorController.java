package com.tvd12.ezydata.example.jpa.controller;

import com.tvd12.ezydata.example.jpa.converter.DataToResponseConverter;
import com.tvd12.ezydata.example.jpa.converter.RequestToDataConverter;
import com.tvd12.ezydata.example.jpa.data.AddAuthorData;
import com.tvd12.ezydata.example.jpa.data.AuthorData;
import com.tvd12.ezydata.example.jpa.request.AddAuthorRequest;
import com.tvd12.ezydata.example.jpa.response.AuthorResponse;
import com.tvd12.ezydata.example.jpa.service.AuthorService;
import com.tvd12.ezydata.example.jpa.validator.AuthorValidator;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller("/api/v1/author")
public class AuthorController {
    private final AuthorValidator authorValidator;
    private final AuthorService authorService;
    private final RequestToDataConverter requestToDataConverter;
    private final DataToResponseConverter dataToResponseConverter;

    @DoPost("/add")
    public AuthorResponse addAuthor(@RequestBody AddAuthorRequest request) {
        authorValidator.validate(request);
        final AddAuthorData addAuthorData = requestToDataConverter.toData(request);
        final AuthorData authorData = authorService.saveAuthor(addAuthorData);
        return dataToResponseConverter.toResponse(authorData);
    }

    @DoGet("/{authorId}")
    public AuthorResponse getAuthor(@PathVariable long authorId) {
        final AuthorData authorData = authorService.getAuthor(authorId);
        return dataToResponseConverter.toResponse(authorData);
    }
}