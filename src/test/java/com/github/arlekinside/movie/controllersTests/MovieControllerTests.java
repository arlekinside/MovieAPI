package com.github.arlekinside.movie.controllersTests;

import com.github.arlekinside.movie.models.Movie;
import com.github.arlekinside.movie.repositories.MovieRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@Order(4)
public class MovieControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MovieRepository repository;
    @Autowired
    private Gson gson;

    private Movie movie;

    @BeforeEach
    public void init() {
        movie = new Movie();
        movie.setId("tt1");
        movie.setTitle("title");
        movie.setDescription("0000");
        repository.save(movie);
    }

    @Test
    public void whenGetAll_thenStatus200_andMovieMatchesResponse() throws Exception {
        mvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(gson.toJson(movie.getId()))));
    }

    @Test
    public void whenGetMovie_thenStatus200_andResponseMovieMatchesTest() throws Exception {
        mvc.perform(get("/movies/tt1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(gson.toJson(movie.getId()))));
    }

    @AfterEach
    public void finish() {
        repository.delete(movie);
    }
}
