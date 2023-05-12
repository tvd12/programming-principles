package com.tvd12.ezydata.example.jpa.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AddBookRequest {
    private Long categoryId;
    private Long authorId;
    private String bookName;
    private BigDecimal price;
    private LocalDate releaseDate;
    private LocalDateTime releaseTime;
}