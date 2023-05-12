package com.tvd12.ezydata.example.jpa.converter;

import com.tvd12.ezydata.example.jpa.data.AddAuthorData;
import com.tvd12.ezydata.example.jpa.data.AddBookData;
import com.tvd12.ezydata.example.jpa.data.AddCategoryData;
import com.tvd12.ezydata.example.jpa.request.AddAuthorRequest;
import com.tvd12.ezydata.example.jpa.request.AddBookRequest;
import com.tvd12.ezydata.example.jpa.request.AddCategoryRequest;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class RequestToDataConverter {

    public AddAuthorData toData(AddAuthorRequest request) {
        return AddAuthorData.builder()
            .authorName(request.getAuthorName())
            .build();
    }

    public AddCategoryData toData(AddCategoryRequest request) {
        return AddCategoryData.builder()
            .categoryName(request.getCategoryName())
            .build();
    }

    public AddBookData toData(AddBookRequest request) {
        return AddBookData.builder()
            .bookName(request.getBookName())
            .authorId(request.getAuthorId())
            .categoryId(request.getCategoryId())
            .price(request.getPrice())
            .releaseDate(request.getReleaseDate())
            .releaseTime(request.getReleaseTime())
            .build();
    }
}