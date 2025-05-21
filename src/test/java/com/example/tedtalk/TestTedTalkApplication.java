package com.example.tedtalk;

import org.springframework.boot.SpringApplication;

public class TestTedTalkApplication {

    public static void main(String[] args) {
        SpringApplication.from(TedTalkApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
