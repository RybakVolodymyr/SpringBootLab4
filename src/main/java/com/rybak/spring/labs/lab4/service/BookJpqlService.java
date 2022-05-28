package com.rybak.spring.labs.lab4.service;

import com.rybak.spring.labs.lab4.jpa.entity.Author;
import com.rybak.spring.labs.lab4.jpa.entity.Book;
import com.rybak.spring.labs.lab4.jpa.entity.Genre;
import com.rybak.spring.labs.lab4.jpa.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookJpqlService {

    private BookRepository repository;

    private GenreService genreService;

    private AuthorService authorService;

    public List<Book> booksByAuthor(String authorId) {
        Author author = authorService.findById(authorId);
        return repository.jpqlBooksByAuthor(author);
    }

    public List<Book> booksByAuthors(List<String> authorIds) {
        List<Author> author = authorService.findByIds(authorIds);
        return repository.jpqlBooksByAuthors(author);
    }

    public List<Book> booksByAuthorsAtLeastOne(List<String> authorIds) {
        List<Author> author = authorService.findByIds(authorIds);
        return repository.jpqlBooksByAuthorsAtLeastOne(author);
    }

    public List<Book> booksByGenre(String genreId) {
        Genre genre = genreService.findById(genreId);
        return repository.jpqlBooksByGenre(genre);
    }

    public List<Book> booksByGenresAtLeastOne(List<String> genreIds) {
        List<Genre> genres = genreService.findByIds(genreIds);
        return repository.jpqlBooksByGenresAtLeastOne(genres);
    }

    public List<Book> booksByGenres(List<String> genreIds) {
        List<Genre> genres = genreService.findByIds(genreIds);
        return repository.jpqlBooksByGenres(genres);
    }

    public List<Book> booksByPrice(Double price) {
        return repository.jpqlBooksByPrice(price);
    }

    public List<Book> booksByPriceRange(Double priceFrom, Double priceTo) {
        return repository.jpqlBooksByPriceRange(priceFrom, priceTo);
    }

    public List<Book> booksByPublishingHouse(String publishingHouse) {
        return repository.jpqlBooksByPublishingHouse(publishingHouse);
    }

    public List<Book> booksByPublishingHousesAtLeastOne(List<String> publishingHouses) {
        return repository.jpqlBooksByPublishingHousesAtLeastOne(publishingHouses);
    }

    public List<Book> booksByPages(Integer pages) {
        return repository.jpqlBooksByPages(pages);
    }

    public List<Book> booksByPagesRange(Integer pagesFrom, Integer pagesTo) {
        return repository.jpqlBooksByPagesRange(pagesFrom, pagesTo);
    }

    public List<Book> booksByTextFragment(String text) {
        if(text == null)
            return null;
        return repository.jpqlBooksByTextFragment(text);
    }
}
