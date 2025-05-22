package com.example.tedtalk.integration;

import com.example.tedtalk.TedTalkApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TedTalkApplication.class)
public class TedTalkControllerSearchIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSpeakerSearchByTalksAndViews() throws Exception {
        mockMvc.perform(get("/api/speakers?size=2&sort=talks,DESC&sort=views,DESC"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content[0].author").value("Alex Gendler"))
                .andExpect(jsonPath("$.content[0].talks").value(45))
                .andExpect(jsonPath("$.content[1].author").value("Iseult Gillespie"))
                .andExpect(jsonPath("$.content[1].talks").value(33))
                .andExpect(jsonPath("$.totalPages").value(2220));
    }

    @Test
    void testSpeakerSearchByRatio() throws Exception {
        mockMvc.perform(get("/api/speakers?size=2&sort=ratio,DESC"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content[0].author").value("Coby Everton"))
                .andExpect(jsonPath("$.content[0].ratio").value(3.29))
                .andExpect(jsonPath("$.content[1].author").value("Mackenzie Dalrymple"))
                .andExpect(jsonPath("$.content[1].ratio").value(3.26363636363636363636363636364))
                .andExpect(jsonPath("$.totalPages").value(2220));
    }

    @Test
    void testTalkSearch() throws Exception {
        mockMvc.perform(get("/api/talks?size=2&sort=ratio,DESC"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content[0].ratio").value(3.29))
                .andExpect(jsonPath("$.content[1].ratio").value(3.2636364))
                .andExpect(jsonPath("$.pageable.sort.sorted").value("true"))
                .andExpect(jsonPath("$.totalPages").value(2719))
                .andExpect(jsonPath("$.totalElements").value(5438));
    }

}
