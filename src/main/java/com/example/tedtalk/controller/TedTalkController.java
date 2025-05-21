package com.example.tedtalk.controller;

import com.example.tedtalk.dto.SpeakerPageDto;
import com.example.tedtalk.service.TedTalkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class TedTalkController {

    private TedTalkService tedTalkService;

    @GetMapping("/speakers")
    public Page<SpeakerPageDto> test() {
        return tedTalkService.getSpeakers();
    }
}
