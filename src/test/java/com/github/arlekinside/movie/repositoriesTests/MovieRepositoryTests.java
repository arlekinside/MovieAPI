package com.github.arlekinside.movie.repositoriesTests;

import com.github.arlekinside.movie.models.Movie;
import com.github.arlekinside.movie.repositories.MovieRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureDataJpa
@TestPropertySource(locations = "classpath:application.properties")
@Order(2)
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository repository;

    private Movie movie;

    @BeforeEach
    public void init() {
        movie = new Movie();
        movie.setId("tt1");
        movie.setTitle("Title");
        repository.save(movie);
    }

    @Test
    public void whenFindByTitle_thenResultContainsTestMovie() {
        Assertions.assertTrue(repository.findByTitle(movie.getTitle()).contains(movie));
    }

    @AfterEach
    public void finish() {
        repository.delete(movie);
    }
}
