package com.example.tedtalk.config;

import com.example.tedtalk.service.TedTalkImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Slf4j
@Configuration
public class DataImport {

    @Value("classpath:data/data.csv")
    Resource resourceFile;

    @Bean
    public CommandLineRunner loadData(TedTalkImportService tedTalkImportService) {
        return (args) -> {
            tedTalkImportService.importCSV(resourceFile);
        };
    }
}

