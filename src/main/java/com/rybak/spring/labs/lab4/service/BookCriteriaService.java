package com.rybak.spring.labs.lab4.service;

import com.rybak.spring.labs.lab4.jpa.entity.Book;
import com.rybak.spring.labs.lab4.jpa.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookCriteriaService {

    private BookRepository repository;

    public List<Book> booksByAuthor(String authorId) {
        return repository.criteriaBooksByAuthor(UUID.fromString(authorId));
    }

    public List<Book> booksByAuthors(List<String> authorIds) {
        return repository.criteriaBooksByAuthors(
                authorIds.stream().map(UUID::fromString).collect(Collectors.toList()));
    }

    public List<Book> booksByAuthorsAtLeastOne(List<String> authorIds) {
        return repository.criteriaBooksByAuthorsAtLeastOne(
                authorIds.stream().map(UUID::fromString).collect(Collectors.toList()));
    }

    public List<Book> booksByGenre(String genreId) {
        return repository.criteriaBooksByGenre(UUID.fromString(genreId));
    }

    public List<Book> booksByGenresAtLeastOne(List<String> genreIds) {
        return repository.criteriaBooksByGenresAtLeastOne(
                genreIds.stream().map(UUID::fromString).collect(Collectors.toList()));
    }

    public List<Book> booksByGenres(List<String> genreIds) {
        return repository.criteriaBooksByGenres(
                genreIds.stream().map(UUID::fromString).collect(Collectors.toList()));
    }

    public List<Book> booksByPrice(Double price) {
        return repository.criteriaBooksByPrice(price);
    }

    public List<Book> booksByPriceRange(Double priceFrom, Double priceTo) {
        return repository.criteriaBooksByPriceRange(priceFrom, priceTo);
    }

    public List<Book> booksByPublishingHouse(String publishingHouse) {
        return repository.criteriaBooksByPublishingHouse(publishingHouse);
    }

    public List<Book> booksByPublishingHousesAtLeastOne(List<String> publishingHouses) {
        return repository.criteriaBooksByPublishingHousesAtLeastOne(publishingHouses);
    }

    public List<Book> booksByPages(Integer pages) {
        return repository.criteriaBooksByPages(pages);
    }

    public List<Book> booksByPagesRange(Integer pagesFrom, Integer pagesTo) {
        return repository.criteriaBooksByPagesRange(pagesFrom, pagesTo);
    }

    public List<Book> booksByTextFragment(String text) {
        if (text == null)
            return null;
        return repository.criteriaBooksByTextFragment(text);
    }
}
