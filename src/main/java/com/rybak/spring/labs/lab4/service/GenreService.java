package com.rybak.spring.labs.lab4.service;

import com.rybak.spring.labs.lab4.exception.GenreNotFoundException;
import com.rybak.spring.labs.lab4.jpa.entity.Author;
import com.rybak.spring.labs.lab4.jpa.entity.Book;
import com.rybak.spring.labs.lab4.jpa.entity.Genre;
import com.rybak.spring.labs.lab4.jpa.repository.BookRepository;
import com.rybak.spring.labs.lab4.jpa.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GenreService {

    private GenreRepository repository;


    public Genre findById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new GenreNotFoundException(String.format("Genre with id = %s not found", id)));
    }

    public List<Genre> findByIds(List<String> id) {
        return repository.findAllById(id.stream().map(UUID::fromString).collect(Collectors.toList()));
    }

}
