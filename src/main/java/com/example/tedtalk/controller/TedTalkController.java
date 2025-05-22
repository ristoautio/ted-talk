package com.example.tedtalk.controller;

import com.example.tedtalk.dto.SpeakerPageDto;
import com.example.tedtalk.dto.TedTalkCreateUpdateDto;
import com.example.tedtalk.dto.TedTalkViewDto;
import com.example.tedtalk.dto.TedTalkViewWithRatioDto;
import com.example.tedtalk.service.TedTalkService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class TedTalkController {

    private TedTalkService tedTalkService;

    @GetMapping("/speakers")
    public Page<SpeakerPageDto> searchSpeakers(Pageable pageable) {return tedTalkService.getSpeakers(pageable);}

    @GetMapping("/talks")
    public Page<TedTalkViewWithRatioDto> searchTalks(Pageable pageable) {
        return tedTalkService.searchTalks(pageable);
    }

    @PostMapping("/talk")
    public TedTalkViewDto addTalk(@Valid @RequestBody TedTalkCreateUpdateDto createDto) {
        return tedTalkService.addTalk(createDto);
    }

    @PatchMapping("/talk/{id}")
    public TedTalkViewDto updateTalk(@PathVariable Long id, @RequestBody TedTalkCreateUpdateDto createDto) {
        return tedTalkService.updateTalk(id, createDto);
    }

}
