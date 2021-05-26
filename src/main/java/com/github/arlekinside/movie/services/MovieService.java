package com.github.arlekinside.movie.services;

import com.github.arlekinside.movie.models.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    Movie get(String field) throws IOException;

    void save(Movie movie);

    void delete(Movie movie);

}
