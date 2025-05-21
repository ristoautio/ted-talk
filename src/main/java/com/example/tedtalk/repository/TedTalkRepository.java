package com.example.tedtalk.repository;

import com.example.tedtalk.dto.SpeakerPageDto;
import com.example.tedtalk.entity.TedTalkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TedTalkRepository extends PagingAndSortingRepository<TedTalkEntity, Long>, CrudRepository<TedTalkEntity, Long> {

    @Query("SELECT t FROM tedTalk t")
    Page<SpeakerPageDto> find(Pageable page);

}
