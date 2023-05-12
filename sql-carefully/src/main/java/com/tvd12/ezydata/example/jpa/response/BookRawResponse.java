package com.tvd12.ezydata.example.jpa.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class BookRawResponse {
    private final Long bookId;
    private final Long categoryId;
    private final Long authorId;
    private final String bookName;
    private final BigDecimal price;
    private final LocalDate releaseDate;
    private final LocalDateTime releaseTime;
}