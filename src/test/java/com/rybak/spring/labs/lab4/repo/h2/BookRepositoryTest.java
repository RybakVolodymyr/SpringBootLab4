package com.rybak.spring.labs.lab4.repo.h2;

import com.rybak.spring.labs.lab4.basetest.H2ApplicationTest;
import com.rybak.spring.labs.lab4.jpa.entity.Author;
import com.rybak.spring.labs.lab4.jpa.entity.Book;
import com.rybak.spring.labs.lab4.jpa.repository.AuthorRepository;
import com.rybak.spring.labs.lab4.jpa.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@H2ApplicationTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAllBooksTest() {
        List<Book> books = bookRepository.findAll();
        assertEquals(5, books.size());

        List<String> bookTitles = new ArrayList<>(Arrays.asList("War and Peace",
                "Ulysses",
                "SomeBook1", "SomeBook2", "SomeBook3"));

        List<String> actualBooksNames = books
                .stream()
                .map(Book::getName)
                .collect(Collectors.toList());

        assertEquals( true, actualBooksNames.containsAll(bookTitles) && bookTitles.containsAll(actualBooksNames));
    }
    @Test
    void findAllBooksByAuthorTest() {

        Author author = authorRepository.getById(UUID.fromString("88b09c93-8123-410d-b887-771e1b9b392b"));
        assertEquals("Leo Tolstoy", author.getName());
        List<Book> books = bookRepository.jpqlBooksByAuthor(author);
        assertEquals(1, books.size());
        assertEquals("War and Peace",books.get(0).getName());

    }
}
