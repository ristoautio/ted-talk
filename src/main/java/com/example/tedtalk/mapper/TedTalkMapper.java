package com.example.tedtalk.mapper;


import com.example.tedtalk.entity.TedTalkEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TedTalkMapper {

    private static final DateTimeFormatter MONTH_YEAR_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ofPattern("MMMM uuuu"))
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter(Locale.ENGLISH);

    public static TedTalkEntity mapRowValuesToTalkEntity(String[] rowValues) {
        TedTalkEntity talk = new TedTalkEntity();
        talk.setTitle(rowValues[0]);
        talk.setAuthor(rowValues[1]);
        talk.setDate(LocalDate.parse(rowValues[2], MONTH_YEAR_FORMATTER));
        talk.setViews(Long.valueOf(rowValues[3]));
        talk.setLikes(Long.valueOf(rowValues[4]));
        talk.setLink(rowValues[5]);
        return talk;
    }

}
