package com.github.arlekinside.movie.services;

import com.github.arlekinside.movie.models.Movie;

import java.util.List;

public interface FavouritesService {
    List<Movie> getAll(long id);

    void addMovie(long id, Movie movie);

    void deleteMovie(long id, Movie movie);
}
