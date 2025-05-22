package com.example.tedtalk.dto;


import java.time.LocalDate;

public interface TedTalkViewDto {
    Long getId();
    String getAuthor();
    String getTitle();
    Long getLikes();
    Long getViews();
    LocalDate getDate();
    String getLink();
}
