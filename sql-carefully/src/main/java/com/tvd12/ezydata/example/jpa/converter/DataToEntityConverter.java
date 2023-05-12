package com.tvd12.ezydata.example.jpa.converter;

import com.tvd12.ezydata.example.jpa.data.AddAuthorData;
import com.tvd12.ezydata.example.jpa.data.AddBookData;
import com.tvd12.ezydata.example.jpa.data.AddCategoryData;
import com.tvd12.ezydata.example.jpa.entity.Author;
import com.tvd12.ezydata.example.jpa.entity.Book;
import com.tvd12.ezydata.example.jpa.entity.Category;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import java.time.LocalDateTime;

@EzySingleton
public class DataToEntityConverter {
    public Author toEntity(AddAuthorData data) {
        final Author entity = new Author();
        entity.setName(data.getAuthorName());
        entity.setCreatedTime(LocalDateTime.now());
        entity.setUpdatedTime(LocalDateTime.now());
        return entity;
    }

    public Category toEntity(AddCategoryData data) {
        final Category entity = new Category();
        entity.setName(data.getCategoryName());
        entity.setCreatedTime(LocalDateTime.now());
        entity.setUpdatedTime(LocalDateTime.now());
        return entity;
    }

    public Book toEntity(AddBookData data) {
        Book book = new Book();
        book.setCategoryId(data.getCategoryId());
        book.setAuthorId(data.getAuthorId());
        book.setName(data.getBookName());
        book.setPrice(data.getPrice());
        book.setReleaseDate(data.getReleaseDate());
        book.setReleaseTime(data.getReleaseTime());
        book.setCreatedTime(LocalDateTime.now());
        book.setUpdatedTime(LocalDateTime.now());
        return book;
    }
}