package com.example.tedtalk.service;

import com.example.tedtalk.entity.TedTalkEntity;
import com.example.tedtalk.mapper.TedTalkMapper;
import com.example.tedtalk.repository.TedTalkRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Service
@AllArgsConstructor
@Slf4j
public class TedTalkImportService {

    private TedTalkRepository tedTalkRepository;
    private static final Integer COLUMNS_IN_ROW = 6;

    private final CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(false).build();

    public void importCSV(Resource resourceFile) throws IOException, CsvException {
        CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(resourceFile.getInputStream()))
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();

        String[] headerRow = csvReader.readNext();

        if (headerRow.length != COLUMNS_IN_ROW) {
            throw new IllegalStateException("wrong number of columns");
        }

        List<TedTalkEntity> entities = csvReader.readAll().stream()
                .map(getTedTalkEntity())
                .filter(Objects::nonNull)
                .toList();

        tedTalkRepository.saveAll(entities);
        csvReader.close();
    }

    private static Function<String[], TedTalkEntity> getTedTalkEntity() {
        return row -> {
            try {
                return TedTalkMapper.validateAndMapRowValuesToTalkEntity(row);
            } catch (Exception e) {
                log.warn("Could not map row: {}", row);
            }
            return null;
        };
    }

}
