package com.tvd12.ezydata.example.jpa.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorResponse {
    private final long id;
    private final String name;
}
