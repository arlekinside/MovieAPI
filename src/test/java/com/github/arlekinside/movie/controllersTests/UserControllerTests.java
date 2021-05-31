package com.github.arlekinside.movie.controllersTests;

import com.github.arlekinside.movie.models.User;
import com.github.arlekinside.movie.repositories.UserRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
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
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserRepository repository;
    @Autowired
    private Gson gson;

    private User user;

    @BeforeEach
    public void init() {
        user = new User();
        user.setUsername("username");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setId(1L);
        user.setStatus("COMMAND");
        repository.save(user);
    }

    @Test
    public void whenGetAllUsers_thenStatus200_andHasTestUser() throws Exception {
        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(gson.toJson(user))));
    }

    @Test
    public void whenGetUser_thenStatus200_andUserValid() throws Exception {
        mvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(gson.toJson(user))));
    }

    @Test
    public void whenAddUser_thenStatus200() throws Exception {
        mvc.perform(post("/users").header("Content-Type", "application/json").content(gson.toJson(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void whenEditUser_thenStatus200_andUserMatchesResponse() throws Exception {
        user.setUsername("username0");
        mvc.perform(put("/users/1").header("Content-Type", "application/json").content(gson.toJson(user)))
                .andExpect(status().isOk());
        whenGetUser_thenStatus200_andUserValid();
    }

    @Test
    public void whenDeleteUser_thenStatus200_andGetUserStatus404() throws Exception {
        mvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }

    @AfterEach
    public void finish() {
        repository.delete(user);
    }
}
