package com.example.tedtalk.dto;


import java.time.LocalDate;

public interface TedTalkViewWithRatioDto {
    Long getId();
    String getAuthor();
    String getTitle();
    Long getLikes();
    Long getViews();
    LocalDate getDate();
    String getLink();
    Float getRatio();

}
