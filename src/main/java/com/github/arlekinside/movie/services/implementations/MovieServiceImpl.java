package com.github.arlekinside.movie.services.implementations;

import com.github.arlekinside.movie.exceptions.IncorrectMovieException;
import com.github.arlekinside.movie.exceptions.MovieNotFoundException;
import com.github.arlekinside.movie.models.Movie;
import com.github.arlekinside.movie.repositories.MovieRepository;
import com.github.arlekinside.movie.services.MovieService;
import com.github.arlekinside.movie.services.api.ImbdApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;
    private final ImbdApiService service;

    @Autowired
    public MovieServiceImpl(MovieRepository repository, ImbdApiService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public List<Movie> getAll() {
        return repository.findAll();
    }

    @Override
    public Movie get(String field) throws IOException {
        if (field.startsWith("tt")) {
            return repository.findById(field)
                    .orElseThrow(() -> new MovieNotFoundException("No such movie in the database"));
        } else {
            List<Movie> movies;
            Movie temp;
            movies = repository.findByTitle(field);
            if (movies.isEmpty()) {
                temp = service.find(field, "en")
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new MovieNotFoundException("No such a movie on the internet"));
            } else {
                temp = movies.get(0);
            }
            save(temp);
            return temp;
        }
    }

    @Override
    public void save(Movie movie) {
        if (movie.getId().isEmpty()) throw new IncorrectMovieException("Can't save movie without id");
        if (!repository.existsById(movie.getId())) {
            repository.save(movie);
        }
    }

    @Override
    public void delete(Movie movie) {
        repository.delete(movie);
    }
}
