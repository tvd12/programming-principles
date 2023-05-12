package com.tvd12.ezydata.example.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public class CommonEntity {
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
