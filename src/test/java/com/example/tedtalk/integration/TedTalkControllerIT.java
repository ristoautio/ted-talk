package com.example.tedtalk.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TedTalkControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSearch() throws Exception {
        mockMvc.perform(get("/api/speakers"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").value("test"));
    }
}
