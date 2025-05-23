package com.example.tedtalk.mapper;


import com.example.tedtalk.dto.TedTalkCreateUpdateDto;
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

    public static TedTalkEntity validateAndMapRowValuesToTalkEntity(String[] rowValues) {
        TedTalkEntity talk = new TedTalkEntity();
        talk.setTitle(nonEmptyString(rowValues[0]));
        talk.setAuthor(nonEmptyString(rowValues[1]));
        talk.setDate(LocalDate.parse(rowValues[2], MONTH_YEAR_FORMATTER));
        talk.setViews(toNumber(rowValues[3]));
        talk.setLikes(toNumber(rowValues[4]));

        if (talk.getLikes() > talk.getViews()) {
            throw new IllegalStateException("more likes than views");
        }

        talk.setLink(tedTalkUrl(rowValues[5]));
        return talk;
    }

    private static String tedTalkUrl(String url) {
        if (!url.startsWith("https://ted.com/talks")) {
            throw new IllegalStateException("invalid url");
        }
        return url;
    }

    private static String nonEmptyString(String str) {
        if (str.isEmpty()) {
            throw new IllegalStateException("String should not be empty");
        }
        return str;
    }

    private static Long toNumber(String str) {
        long value = Long.parseLong(str);
        if(value < 0){
            throw new IllegalArgumentException("should be positive");
        }
        return value;
    }

    public static TedTalkEntity mapDtoToEntity(TedTalkCreateUpdateDto createDto) {
        TedTalkEntity talk = new TedTalkEntity();
        talk.setTitle(createDto.getTitle());
        talk.setAuthor(createDto.getAuthor());
        talk.setDate(createDto.getDate());
        talk.setViews(createDto.getViews());
        talk.setLikes(createDto.getLikes());
        talk.setLink(createDto.getLink());
        return talk;
    }
}
