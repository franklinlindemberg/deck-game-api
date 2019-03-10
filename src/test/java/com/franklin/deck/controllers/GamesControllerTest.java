package com.franklin.deck.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.franklin.deck.entities.Game;
import com.franklin.deck.services.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GamesControllerTest {

    private static final String BASE_URL = "/v1/games/";

    @Autowired
    GameService gameService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mvc;

    // Used for converting heroes to/from JSON
    private ObjectMapper mapper = new ObjectMapper();


    @Before
    public void initTests() {
        jdbcTemplate.execute("delete from games; ");
    }

    @Test
    public void shouldCreateGameWhenPut() throws Exception {
        String gameName = "test";
        Game game = new Game();

        game.setName(gameName);

        byte[] gameJson = this.mapper.writeValueAsString(game).getBytes();

        mvc.perform(MockMvcRequestBuilders
                .put(BASE_URL)
                .content(gameJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void shouldDeleteGameWhenDelete() throws Exception {
        String gameName = "test";
        Game game = new Game();

        game.setName(gameName);

        byte[] gameJson = this.mapper.writeValueAsString(game).getBytes();

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .put(BASE_URL)
                .content(gameJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        mvc.perform(MockMvcRequestBuilders
                .delete(BASE_URL + result.getResponse().getContentAsString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}