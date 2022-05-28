package com.rybak.spring.labs.lab4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenreNotFoundException extends RuntimeException {

    public GenreNotFoundException() {
    }

    public GenreNotFoundException(String message) {
        super(message);
    }
}