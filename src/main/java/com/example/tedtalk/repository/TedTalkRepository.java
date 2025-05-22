package com.example.tedtalk.repository;

import com.example.tedtalk.dto.SpeakerPageDto;
import com.example.tedtalk.dto.TedTalkViewDto;
import com.example.tedtalk.dto.TedTalkViewWithRatioDto;
import com.example.tedtalk.entity.TedTalkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TedTalkRepository extends PagingAndSortingRepository<TedTalkEntity, Long>, CrudRepository<TedTalkEntity, Long> {

    @Query(value = "SELECT ted_talk.author, count(ted_talk.author) as talks, sum(ted_talk.views) as views, sum(ted_talk.likes) as likes, " +
            "100.0*(sum(ted_talk.likes)/CAST(sum(ted_talk.views) AS float)) as ratio FROM ted_talk group by author", nativeQuery = true)
    Page<SpeakerPageDto> findWithEngagementRatioForSpeaker(Pageable pageable);

    @Query(value = "SELECT ted_talk.*, 100.0*(ted_talk.likes/CAST(ted_talk.views AS float)) as ratio FROM ted_talk", nativeQuery = true)
    Page<TedTalkViewWithRatioDto> findWithEngagementRatio(Pageable pageable);

    @Query("SELECT t FROM tedTalk t where t.id = ?1")
    Optional<TedTalkViewDto> getTedTalkById(Long id);
}
