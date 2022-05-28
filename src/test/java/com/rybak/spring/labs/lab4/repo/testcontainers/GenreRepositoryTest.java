package com.rybak.spring.labs.lab4.repo.testcontainers;

import com.rybak.spring.labs.lab4.basetest.TestcontainersApplicationTest;
import com.rybak.spring.labs.lab4.jpa.entity.Genre;
import com.rybak.spring.labs.lab4.jpa.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@TestcontainersApplicationTest
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void findAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        assertEquals(5, genres.size());

        List<String> expectedGenreNames = new ArrayList<>(Arrays.asList("Novel",
                "Historical Fiction",
                "SomeGenre1", "SomeGenre2", "SomeGenre3"));

        List<String> actualGenreNames = genres
                .stream()
                .map(Genre::getName)
                .collect(Collectors.toList());

        assertEquals(true, actualGenreNames.containsAll(expectedGenreNames) && expectedGenreNames.containsAll(actualGenreNames));
    }

}
