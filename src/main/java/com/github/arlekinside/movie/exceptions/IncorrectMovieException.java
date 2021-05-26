package com.github.arlekinside.movie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectMovieException extends RuntimeException {

    public IncorrectMovieException(String message) {
        super(message);
    }
}
