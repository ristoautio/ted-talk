package com.example.tedtalk.service;

import com.example.tedtalk.dto.SpeakerPageDto;
import com.example.tedtalk.dto.TedTalkCreateUpdateDto;
import com.example.tedtalk.dto.TedTalkViewDto;
import com.example.tedtalk.dto.TedTalkViewWithRatioDto;
import com.example.tedtalk.entity.TedTalkEntity;
import com.example.tedtalk.mapper.TedTalkMapper;
import com.example.tedtalk.repository.TedTalkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TedTalkService {

    private TedTalkRepository tedTalkRepository;

    public Page<SpeakerPageDto> getSpeakers(Pageable pageable) {
        return tedTalkRepository.findWithEngagementRatioForSpeaker(pageable);
    }

    public Page<TedTalkViewWithRatioDto> searchTalks(Pageable pageable) {
        return tedTalkRepository.findWithEngagementRatio(pageable);
    }

    public TedTalkViewDto addTalk(TedTalkCreateUpdateDto createDto) {
        TedTalkEntity talk = TedTalkMapper.mapDtoToEntity(createDto);
        return saveAndReturnViewDto(talk);
    }
    public TedTalkViewDto updateTalk(Long id, TedTalkCreateUpdateDto updateDto) {
        TedTalkEntity talk = tedTalkRepository.findById(id).orElseThrow(TalkNotFoundException::new);

        Optional.ofNullable(updateDto.getAuthor()).ifPresent(talk::setAuthor);
        Optional.ofNullable(updateDto.getTitle()).ifPresent(talk::setTitle);
        Optional.ofNullable(updateDto.getLikes()).ifPresent(talk::setLikes);
        Optional.ofNullable(updateDto.getViews()).ifPresent(talk::setViews);
        Optional.ofNullable(updateDto.getDate()).ifPresent(talk::setDate);
        Optional.ofNullable(updateDto.getLink()).ifPresent(talk::setLink);

        return saveAndReturnViewDto(talk);
    }

    private TedTalkViewDto saveAndReturnViewDto(TedTalkEntity talk) {
        talk = tedTalkRepository.save(talk);
        return tedTalkRepository.getTedTalkById(talk.getId()).orElseThrow(TalkNotFoundException::new);
    }

}
