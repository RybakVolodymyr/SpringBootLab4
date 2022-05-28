package com.rybak.spring.labs.lab4.service;

import com.rybak.spring.labs.lab4.exception.AuthorNotFoundException;
import com.rybak.spring.labs.lab4.jpa.entity.Author;
import com.rybak.spring.labs.lab4.jpa.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository repository;

    public Author findById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Author with id = %s not found", id)));
    }

    public List<Author> findByIds(List<String> id) {
        return repository.findAllById(id.stream().map(UUID::fromString).collect(Collectors.toList()));
    }
}
