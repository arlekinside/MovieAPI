package com.github.arlekinside.movie.controllers;

import com.github.arlekinside.movie.models.Movie;
import com.github.arlekinside.movie.services.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/favourites")
public class FavouritesController {

    private FavouritesService service;

    @Autowired
    public FavouritesController(FavouritesService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Movie> getALL(@PathVariable("id") String id) {
        return service.getAll(id);
    }

    @PostMapping("")
    public void addMovie(@PathVariable("id") String id, @RequestBody Movie movie) {
        service.addMovie(id, movie);
    }

    @DeleteMapping("")
    public void deleteMovie(@PathVariable("id") String id, @RequestBody Movie movie) {
        service.deleteMovie(id, movie);
    }
}
