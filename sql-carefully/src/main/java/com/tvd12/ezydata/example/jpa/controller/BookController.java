package com.tvd12.ezydata.example.jpa.controller;

import com.tvd12.ezydata.example.jpa.converter.DataToResponseConverter;
import com.tvd12.ezydata.example.jpa.converter.RequestToDataConverter;
import com.tvd12.ezydata.example.jpa.data.AddBookData;
import com.tvd12.ezydata.example.jpa.data.BookData;
import com.tvd12.ezydata.example.jpa.data.BookRawData;
import com.tvd12.ezydata.example.jpa.request.AddBookRequest;
import com.tvd12.ezydata.example.jpa.response.BookRawResponse;
import com.tvd12.ezydata.example.jpa.response.BookResponse;
import com.tvd12.ezydata.example.jpa.service.BookService;
import com.tvd12.ezydata.example.jpa.validator.BookValidator;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Controller("/api/v1")
public class BookController {
    private final BookValidator bookValidator;
    private final BookService bookService;
    private final RequestToDataConverter requestToDataConverter;
    private final DataToResponseConverter dataToResponseConverter;

    @DoPost("/book/add/{count}")
    public boolean addBooks(@PathVariable int count) {
        bookService.addBooks(count);
        return Boolean.TRUE;
    }

    @DoPost("/book/add")
    public BookResponse addBook(@RequestBody AddBookRequest request) {
        bookValidator.validate(request);
        final AddBookData addBookData = requestToDataConverter.toData(request);
        final BookData bookData = bookService.addBook(addBookData);
        return dataToResponseConverter.toResponse(bookData);
    }

    @DoGet("/book/list")
    public List<BookRawResponse> getBooks() {
        final List<BookRawData> dataList = bookService.getAllBooks();
        return dataToResponseConverter.toRawResponseList(dataList);
    }

    @DoGet("/book/list-by-offset")
    public List<BookRawResponse> getBooksByOffset(
        @RequestParam("offset") int offset,
        @RequestParam("limit") int limit
    ) {
        final List<BookRawData> dataList = bookService.getBooks(offset, limit);
        return dataToResponseConverter.toRawResponseList(dataList);
    }

    @DoGet("/book/list-by-cursor")
    public List<BookRawResponse> getBooksByCursor(
        @RequestParam("next_page_token") String nextPageToken,
        @RequestParam("limit") int limit
    ) {
        final List<BookRawData> dataList = bookService.getBooks(nextPageToken, limit);
        return dataToResponseConverter.toRawResponseList(dataList);
    }

    @DoGet("/books/{bookId}")
    public BookResponse getBook(@PathVariable Long bookId) {
        final BookData bookData = bookService.getBook(bookId);
        return dataToResponseConverter.toResponse(bookData);
    }

    @DoGet("/books")
    public List<BookResponse> getBooks(
        @RequestParam("lower_than") String lowerThan,
        @RequestParam("upper_than") String upperThan,
        @RequestParam("size") int size
    ) {
        final List<BookData> dataList = bookService.getBooks(
            lowerThan,
            upperThan,
            size
        );
        return dataToResponseConverter.toResponseList(dataList);
    }

    @DoGet("/books/expected-revenue")
    public BigDecimal getExpectedRevenue() {
        return bookService.getExpectedRevenue();
    }
}





