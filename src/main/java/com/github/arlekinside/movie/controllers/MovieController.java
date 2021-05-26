package com.github.arlekinside.movie.controllers;

import com.github.arlekinside.movie.exceptions.MovieNotFoundException;
import com.github.arlekinside.movie.models.Movie;
import com.github.arlekinside.movie.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService service;

    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Movie> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable("id") String id) {
        try {
            return service.get(id);
        } catch (IOException e) {
            throw new MovieNotFoundException("Can't find such movie on the internet");
        }
    }

    @GetMapping("/find")
    public Movie findByTitle(@RequestParam("title") String title) {
        try {
            return service.get(title);
        } catch (IOException e) {
            throw new MovieNotFoundException("Can't find such movie on the internet");
        }
    }
}
