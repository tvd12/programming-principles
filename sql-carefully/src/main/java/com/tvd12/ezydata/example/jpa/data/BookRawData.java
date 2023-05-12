package com.tvd12.ezydata.example.jpa.data;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class BookRawData {
    private final Long id;
    private final Long categoryId;
    private final Long authorId;
    private final String name;
    private final BigDecimal price;
    private final LocalDate releaseDate;
    private final LocalDateTime releaseTime;
}