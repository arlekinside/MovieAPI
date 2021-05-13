package com.github.arlekinside.movie.services;

import com.github.arlekinside.movie.models.Movie;

import java.util.List;

public interface FavouritesService {
    List<Movie> getAll(String id);

    void addMovie(String id, Movie movie);

    void deleteMovie(String id, Movie movie);
}
