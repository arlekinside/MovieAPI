package com.github.arlekinside.movie.services.implementations;

import com.github.arlekinside.movie.exceptions.MovieNotFoundException;
import com.github.arlekinside.movie.exceptions.UserNotFoundException;
import com.github.arlekinside.movie.models.Movie;
import com.github.arlekinside.movie.models.User;
import com.github.arlekinside.movie.repositories.MovieRepository;
import com.github.arlekinside.movie.repositories.UserRepository;
import com.github.arlekinside.movie.services.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    private MovieRepository movieRepository;
    private UserRepository userRepository;

    @Autowired
    public FavouritesServiceImpl(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Movie> getAll(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("---No such user in the darabase---"))
                .getMovies();
    }

    @Override
    public void addMovie(long id, Movie movie) {
        if (!movieRepository.existsById(movie.getId())) movieRepository.save(movie);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("---No such user in the darabase---"));
        List<Movie> temp = user.getMovies();
        temp.add(movie);
        user.setMovies(temp);
        userRepository.save(user);
    }

    @Override
    public void deleteMovie(long id, Movie movie) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("---No such user in the darabase---"));
        List<Movie> temp = user.getMovies();
        if (!temp.contains(movie)) throw new MovieNotFoundException("---No such movie in the list---");
        temp.remove(movie);
        user.setMovies(temp);
        userRepository.save(user);
    }
}
