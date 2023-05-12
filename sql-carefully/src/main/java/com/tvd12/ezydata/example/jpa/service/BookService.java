package com.tvd12.ezydata.example.jpa.service;

import com.tvd12.ezydata.example.jpa.converter.DataToEntityConverter;
import com.tvd12.ezydata.example.jpa.converter.EntityToDataConverter;
import com.tvd12.ezydata.example.jpa.data.AddBookData;
import com.tvd12.ezydata.example.jpa.data.BookData;
import com.tvd12.ezydata.example.jpa.data.BookRawData;
import com.tvd12.ezydata.example.jpa.entity.Author;
import com.tvd12.ezydata.example.jpa.entity.Book;
import com.tvd12.ezydata.example.jpa.entity.Category;
import com.tvd12.ezydata.example.jpa.exception.BookNotFoundException;
import com.tvd12.ezydata.example.jpa.exception.DuplicatedBookException;
import com.tvd12.ezydata.example.jpa.exception.InvalidAuthorIdException;
import com.tvd12.ezydata.example.jpa.exception.InvalidCategoryIdException;
import com.tvd12.ezydata.example.jpa.repository.AuthorRepository;
import com.tvd12.ezydata.example.jpa.repository.BookRepository;
import com.tvd12.ezydata.example.jpa.repository.CategoryRepository;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.io.EzyStrings;
import com.tvd12.ezyfox.util.Next;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final EntityToDataConverter entityToDataConverter;
    private final DataToEntityConverter dataToEntityConverter;

    public void addBooks(int count) {
        List<Book> books = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < count; ++i) {
            AddBookData data = AddBookData.builder()
                .bookName(String.format("%03d", random.nextInt(1000)))
                .authorId((long) random.nextInt(0, Integer.MAX_VALUE))
                .categoryId((long) random.nextInt(0, Integer.MAX_VALUE))
                .price(BigDecimal.valueOf(random.nextInt(0, Integer.MAX_VALUE)))
                .releaseDate(LocalDate.now())
                .releaseTime(LocalDateTime.now())
                .build();
            books.add(dataToEntityConverter.toEntity(data));
        }
        bookRepository.save(books);
    }

    public BookData addBook(AddBookData data) {
        Book existedBook = bookRepository.findByNameAndAuthorId(
            data.getBookName(),
            data.getAuthorId()
        );
        if (existedBook != null) {
            throw new DuplicatedBookException(
                "author: " + data.getAuthorId() +
                    " has already registered book: " + data.getBookName()
            );
        }
        final Author author = authorRepository.findById(data.getAuthorId());
        if (author == null) {
            throw new InvalidAuthorIdException(
                "author: " + data.getAuthorId() + " not found"
            );
        }
        final Category category = categoryRepository.findById(data.getCategoryId());
        if (category == null) {
            throw new InvalidCategoryIdException(
                "category: " + data.getCategoryId() + " not found"
            );
        }

        final Book book = dataToEntityConverter.toEntity(data);
        bookRepository.save(book);
        return entityToDataConverter.toData(book, author, category);
    }

    public BookData getBook(Long bookId) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            throw new BookNotFoundException("not found book with id: " + bookId);
        }
        final Author author = authorRepository.findById(book.getAuthorId());
        final Category category = categoryRepository.findById(book.getCategoryId());
        return entityToDataConverter.toData(book, author, category);
    }

    public List<BookRawData> getAllBooks() {
        return bookRepository.findAll()
            .stream()
            .map(entityToDataConverter::toData)
            .collect(Collectors.toList());
    }

    public List<BookRawData> getBooks(int skip, int limit) {
        return bookRepository.findBooksOrderByPriceAndId(
                Next.fromSkipLimit(skip, limit)
            )
            .stream()
            .map(entityToDataConverter::toData)
            .collect(Collectors.toList());
    }

    public List<BookRawData> getBooks(String nextPageToken, int limit) {
        if (EzyStrings.isNoContent(nextPageToken)) {
            return getBooks(0, limit);
        }
        String[] strs = nextPageToken.split(":");
        BigInteger priceExclusive = new BigInteger(strs[0]);
        Long bookId = Long.valueOf(strs[1]);
        return bookRepository.findBooks(
                priceExclusive,
                bookId,
                Next.fromSkipLimit(0, limit)
            )
            .stream()
            .map(entityToDataConverter::toData)
            .collect(Collectors.toList());
    }

    public List<BookData> getBooks(
        String lowerThan,
        String upperThan,
        int size
    ) {
        List<Book> books;
        if (EzyStrings.isEmpty(upperThan)) {
            if (EzyStrings.isEmpty(lowerThan)) {
                books = bookRepository.findBooks(Next.fromSkipLimit(0, size));
            } else {
                books = bookRepository.findByNameLt(lowerThan, Next.fromSkipLimit(0, size));
            }
        } else {
            books = bookRepository.findByNameGt(upperThan, Next.fromSkipLimit(0, size));
        }
        final List<Long> authorIds = books.stream()
            .map(Book::getAuthorId)
            .collect(Collectors.toList());
        final List<Long> categoryIds = books.stream()
            .map(Book::getCategoryId)
            .collect(Collectors.toList());
        final Map<Long, Author> authors = authorRepository.findListByIds(authorIds)
            .stream()
            .collect(Collectors.toMap(Author::getId, it -> it));
        final Map<Long, Category> categories = categoryRepository.findListByIds(categoryIds)
            .stream()
            .collect(Collectors.toMap(Category::getId, it -> it));
        return entityToDataConverter.toDataList(books, authors, categories);
    }

    public BigDecimal getExpectedRevenue() {
        return bookRepository.sumPrice().getSum();
    }

}
