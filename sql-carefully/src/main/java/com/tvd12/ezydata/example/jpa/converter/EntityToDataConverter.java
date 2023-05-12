package com.tvd12.ezydata.example.jpa.converter;

import com.tvd12.ezydata.example.jpa.data.AuthorData;
import com.tvd12.ezydata.example.jpa.data.BookData;
import com.tvd12.ezydata.example.jpa.data.BookRawData;
import com.tvd12.ezydata.example.jpa.data.CategoryData;
import com.tvd12.ezydata.example.jpa.entity.Author;
import com.tvd12.ezydata.example.jpa.entity.Book;
import com.tvd12.ezydata.example.jpa.entity.Category;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EzySingleton
public class EntityToDataConverter {
    public AuthorData toData(Author author) {
        if (author == null) {
            return null;
        }
        return AuthorData.builder()
            .id(author.getId())
            .name(author.getName())
            .build();
    }

    public CategoryData toData(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryData.builder()
            .id(category.getId())
            .name(category.getName())
            .build();
    }

    public BookData toData(
        Book book,
        Author author,
        Category category
    ) {
        return BookData.builder()
            .id(book.getId())
            .name(book.getName())
            .author(toData(author))
            .category(toData(category))
            .price(book.getPrice())
            .releaseDate(book.getReleaseDate())
            .releaseTime(book.getReleaseTime())
            .build();
    }

    public List<BookData> toDataList(
        List<Book> books,
        Map<Long, Author> authors,
        Map<Long, Category> categories
    ) {
        return books.stream().map(it ->
            toData(
                it,
                authors.get(it.getAuthorId()),
                categories.get(it.getCategoryId())
            )
        ).collect(Collectors.toList());
    }

    public BookRawData toData(Book book) {
        return BookRawData.builder()
            .id(book.getId())
            .name(book.getName())
            .authorId(book.getAuthorId())
            .categoryId(book.getCategoryId())
            .price(book.getPrice())
            .releaseDate(book.getReleaseDate())
            .releaseTime(book.getReleaseTime())
            .build();
    }

    public List<BookRawData> toDataList(List<Book> books) {
        return books.stream()
            .map(this::toData)
            .collect(Collectors.toList());
    }
}
