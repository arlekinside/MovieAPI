package com.github.arlekinside.movie.servicesTests;

import com.github.arlekinside.movie.models.Movie;
import com.github.arlekinside.movie.repositories.MovieRepository;
import com.github.arlekinside.movie.services.MovieService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Order(4)
public class MovieServiceTests {

    @Autowired
    private MovieService service;

    @Autowired
    private MovieRepository repository;

    private Movie movie;

    @BeforeEach
    public void init(){
        movie = new Movie();
        movie.setId("tt1");
        movie.setTitle("title");
        movie.setDescription("0000");
        repository.save(movie);
    }

    @Test
    public void whenGetAll_thenContainsTestMovie(){
        Assertions.assertTrue(service.getAll().contains(movie));
    }

    @Test
    public void whenGetMovie_thenEqualsTestMovie() throws IOException {
        Assertions.assertEquals(movie, service.get(movie.getId()));
    }

    @Test
    public void whenSaveMovie_thenGetAllContainsTestMovie(){
        Movie m = new Movie();
        m.setId("tt2");
        m.setTitle("title");
        m.setDescription("0000");
        service.save(m);
        Assertions.assertTrue(service.getAll().contains(m));
        repository.delete(m);
    }

    @Test
    public void whenDelete_thenGetAllMoviesDoesntContainTestMovie(){
        Movie m = new Movie();
        m.setId("tt2");
        m.setTitle("title");
        m.setDescription("0000");
        repository.save(m);
        service.delete(m);
        Assertions.assertFalse(service.getAll().contains(m));
    }
    @AfterEach
    public void finish(){
        repository.delete(movie);
    }
}
