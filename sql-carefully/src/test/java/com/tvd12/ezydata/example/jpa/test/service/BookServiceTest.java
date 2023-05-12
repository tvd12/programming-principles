package com.tvd12.ezydata.example.jpa.test.service;

import com.tvd12.ezydata.example.jpa.converter.DataToEntityConverter;
import com.tvd12.ezydata.example.jpa.converter.EntityToDataConverter;
import com.tvd12.ezydata.example.jpa.data.AddBookData;
import com.tvd12.ezydata.example.jpa.data.AuthorData;
import com.tvd12.ezydata.example.jpa.data.BookData;
import com.tvd12.ezydata.example.jpa.data.CategoryData;
import com.tvd12.ezydata.example.jpa.entity.Author;
import com.tvd12.ezydata.example.jpa.entity.Book;
import com.tvd12.ezydata.example.jpa.entity.Category;
import com.tvd12.ezydata.example.jpa.exception.DuplicatedBookException;
import com.tvd12.ezydata.example.jpa.repository.AuthorRepository;
import com.tvd12.ezydata.example.jpa.repository.BookRepository;
import com.tvd12.ezydata.example.jpa.repository.CategoryRepository;
import com.tvd12.ezydata.example.jpa.service.BookService;
import com.tvd12.test.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static com.tvd12.test.assertion.Asserts.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private EntityToDataConverter entityToDataConverter;
    @Mock
    private DataToEntityConverter dataToEntityConverter;
    @InjectMocks
    private BookService sut;

    @Test
    public void addBookSuccess() {
        // given
        final AddBookData addBookData = randomAddBookData();

        when(
            bookRepository.findByNameAndAuthorId(
                addBookData.getBookName(),
                addBookData.getAuthorId()
            )
        ).thenReturn(null);

        final Author author = new Author(
            RandomUtil.randomLong(),
            RandomUtil.randomShortAlphabetString()
        );
        when(authorRepository.findById(addBookData.getAuthorId())).thenReturn(author);

        final Category category = new Category(
            RandomUtil.randomLong(),
            RandomUtil.randomShortAlphabetString()
        );
        when(categoryRepository.findById(addBookData.getCategoryId())).thenReturn(category);

        final Book book = new Book(
            0L,
            addBookData.getCategoryId(),
            addBookData.getAuthorId(),
            addBookData.getBookName(),
            addBookData.getPrice(),
            addBookData.getReleaseDate(),
            addBookData.getReleaseTime(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        when(dataToEntityConverter.toEntity(addBookData)).thenReturn(book);

        doAnswer(it -> {
            Book inputBook = (Book) it.getArguments()[0];
            inputBook.setId(RandomUtil.randomLong());
            return null;
        }).when(bookRepository).save(book);

        BookData bookData = BookData.builder()
            .id(book.getId())
            .name(book.getName())
            .author(
                AuthorData.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .build()
            )
            .category(
                CategoryData.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build()
            )
            .price(addBookData.getPrice())
            .releaseDate(addBookData.getReleaseDate())
            .releaseTime(addBookData.getReleaseTime())
            .build();
        when(entityToDataConverter.toData(book, author, category)).thenReturn(bookData);

        // when
        BookData actual = sut.addBook(addBookData);

        // then
        assertThat(actual).isEqualsTo(bookData);

        verify(bookRepository, times(1)).findByNameAndAuthorId(
            addBookData.getBookName(),
            addBookData.getAuthorId()
        );

        verify(authorRepository, times(1)).findById(
            addBookData.getAuthorId()
        );

        verify(categoryRepository, times(1)).findById(
            addBookData.getCategoryId()
        );

        verify(bookRepository, times(1)).save(
            book
        );

        verify(dataToEntityConverter, times(1)).toEntity(
            addBookData
        );

        verify(entityToDataConverter, times(1)).toData(
            book, author, category
        );
        validateMockitoUsage();
    }

    @Test
    public void addBookFailedDueToBookRepository() {
        // given
        final AddBookData addBookData = randomAddBookData();

        final Book book = new Book(
            RandomUtil.randomLong(),
            addBookData.getCategoryId(),
            addBookData.getAuthorId(),
            addBookData.getBookName(),
            addBookData.getPrice(),
            addBookData.getReleaseDate(),
            addBookData.getReleaseTime(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        when(
            bookRepository.findByNameAndAuthorId(
                addBookData.getBookName(),
                addBookData.getAuthorId()
            )
        ).thenReturn(book);

        // when
        final Throwable throwable = assertThrows(() -> sut.addBook(addBookData));

        // then
        assertTrue(throwable instanceof DuplicatedBookException);

        verify(bookRepository, times(1)).findByNameAndAuthorId(
            addBookData.getBookName(),
            addBookData.getAuthorId()
        );
        validateMockitoUsage();
    }

    private AddBookData randomAddBookData() {
        return AddBookData.builder()
            .bookName(RandomUtil.randomShortAlphabetString())
            .price(RandomUtil.random32BitBigDecimal())
            .authorId(RandomUtil.randomLong())
            .categoryId(RandomUtil.randomLong())
            .releaseDate(RandomUtil.randomLocalDate())
            .releaseTime(RandomUtil.randomLocalDateTime())
            .build();
    }
}
