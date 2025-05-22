package com.example.tedtalk.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TedTalkCreateUpdateDto {
    private String title;
    private String author;
    private LocalDate date;

    @PositiveOrZero
    private Long views;
    @PositiveOrZero
    private Long likes;

    private String link;
}
