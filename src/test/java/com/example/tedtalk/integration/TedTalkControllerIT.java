package com.example.tedtalk.integration;

import com.example.tedtalk.TedTalkApplication;
import com.example.tedtalk.entity.TedTalkEntity;
import com.example.tedtalk.repository.TedTalkRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TedTalkApplication.class)
public class TedTalkControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TedTalkRepository tedTalkRepository;

    @Test
    void addTalk() throws Exception {
        mockMvc.perform(post("/api/talk")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "my talk",
                                  "author": "kevin",
                                  "date": "2022-01-01",
                                  "views": 27000,
                                  "likes": 2300,
                                  "link": "https://example.com"
                                }
                                """))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.author").value("kevin"))
                .andExpect(jsonPath("$.title").value("my talk"))
                .andExpect(jsonPath("$.views").value(27000));
    }

    @Test
    void addTalkNegativeLikes() throws Exception {
        mockMvc.perform(post("/api/talk")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "my talk",
                                  "author": "kevin",
                                  "date": "June 2022",
                                  "view": 27000,
                                  "likes": -2300,
                                  "link": "https://example.com"
                                }
                                """))
                .andExpect(status().is4xxClientError());
    }
    @Test
    void updateTalk() throws Exception {
        TedTalkEntity talk = new TedTalkEntity();
        talk.setTitle("test talk");
        talk.setAuthor("Bob");
        talk.setDate(LocalDate.now());
        talk.setViews(123L);
        talk.setLikes(12L);
        talk.setLink("https://example.com");
        talk = tedTalkRepository.save(talk);

        mockMvc.perform(patch("/api/talk/" + talk.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "my talk",
                                  "views": 27000,
                                  "likes": 2300
                                }
                                """))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(talk.getId()))
                .andExpect(jsonPath("$.views").value(27000))
                .andExpect(jsonPath("$.likes").value(2300))
                .andExpect(jsonPath("$.link").value("https://example.com"));
    }

    @Test
    void updateTalkNotFound() throws Exception {
        mockMvc.perform(patch("/api/talk/24234234234234")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "my talk",
                                  "views": 27000,
                                  "likes": 2300
                                }
                                """))
                .andExpect(status().is4xxClientError());
    }
}
