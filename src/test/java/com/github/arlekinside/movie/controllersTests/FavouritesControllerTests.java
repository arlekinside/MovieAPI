package com.github.arlekinside.movie.controllersTests;

import com.github.arlekinside.movie.models.Movie;
import com.github.arlekinside.movie.models.User;
import com.github.arlekinside.movie.repositories.MovieRepository;
import com.github.arlekinside.movie.repositories.UserRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class FavouritesControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Gson gson;

    private User user;

    private Movie movie;

    @BeforeEach
    public void addTestUser() {
        user = new User();
        movie = new Movie();
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setId(1L);
        user.setStatus("COMMAND");
        movie.setId("tt1");
        movie.setTitle("title");
        userRepository.save(user);
    }

    @Test
    public void whenAddMovie_thenResponse200() throws Exception{
        mvc.perform(
                post("/users/1/favourites")
                .header("Content-Type", "application/json")
                .content(gson.toJson(movie))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetAll_thenResponse200_andTestMovieMatchesResponse() throws Exception{
        whenAddMovie_thenResponse200();
        mvc.perform(get("/users/1/favourites"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(gson.toJson(movie.getId()))));
    }
    @Test
    public void whenDeleteMovie_thenResponse200() throws Exception{
        whenAddMovie_thenResponse200();
        mvc.perform(
                delete("/users/1/favourites/")
                .header("Content-Type", "application/json")
                .content(gson.toJson(movie))
        )
                .andExpect(status().isOk());
    }

    @AfterEach
    public void finish(){
        userRepository.delete(user);
    }
}
