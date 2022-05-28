package com.rybak.spring.labs.lab4.repo.testcontainers;

import com.rybak.spring.labs.lab4.basetest.TestcontainersApplicationTest;
import com.rybak.spring.labs.lab4.jpa.entity.Author;
import com.rybak.spring.labs.lab4.jpa.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@TestcontainersApplicationTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAllGenres() {
        List<Author> authors = authorRepository.findAll();
        assertEquals(5, authors.size());

        List<String> expectedAuthorsNames = new ArrayList<>(Arrays.asList("James Joyce",
                "Leo Tolstoy",
                "SomeAuthor1", "SomeAuthor2", "SomeAuthor3"));

        List<String> actualAuthorsNames = authors
                .stream()
                .map(Author::getName)
                .collect(Collectors.toList());

        assertEquals(true, actualAuthorsNames.containsAll(expectedAuthorsNames) && expectedAuthorsNames.containsAll(actualAuthorsNames));
    }
}
