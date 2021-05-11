package com.github.arlekinside.movie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectUserException extends RuntimeException {

    public IncorrectUserException(String message) {
        super(message);
    }
}
