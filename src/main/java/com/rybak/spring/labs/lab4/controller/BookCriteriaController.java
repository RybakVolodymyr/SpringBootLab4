package com.rybak.spring.labs.lab4.controller;

import com.rybak.spring.labs.lab4.jpa.entity.Book;
import com.rybak.spring.labs.lab4.service.BookCriteriaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/book/criteria")
public class BookCriteriaController {

    private BookCriteriaService service;

    @GetMapping("/byAuthor/{id}")
    public List<Book> findBookByAuthorId(@PathVariable("id") String id) {
        return service.booksByAuthor(id);
    }

    @GetMapping("/byAuthors")
    public List<Book> findBookByAuthorIds(@RequestParam("ids") List<String> ids) {
        return service.booksByAuthors(ids);
    }

    @GetMapping("/byAuthorsAtLeastOne")
    public List<Book> findBookByAuthorIdsAtLeastOne(@RequestParam("ids") List<String> ids) {
        return service.booksByAuthorsAtLeastOne(ids);
    }

    @GetMapping("/genre/{id}")
    public List<Book> booksByGenre(@PathVariable("id") String id) {
        return service.booksByGenre(id);
    }

    @GetMapping("/byGenres")
    public List<Book> booksByGenres(List<String> genreIds) {
        return service.booksByGenres(genreIds);
    }

    @GetMapping("/byGenresAtLeastOne")
    public List<Book> booksByGenresAtLeastOne(List<String> genreIds) {
        return service.booksByGenresAtLeastOne(genreIds);
    }

    @GetMapping("/byPrice")
    public List<Book> booksByPrice(Double price) {
        return service.booksByPrice(price);
    }

    @GetMapping("/byPriceRange")
    public List<Book> booksByPriceRange(Double priceFrom, Double priceTo) {
        return service.booksByPriceRange(priceFrom, priceTo);
    }

    @GetMapping("/byPublishingHouse")
    public List<Book> booksByPublishingHouse(String publishingHouse) {
        return service.booksByPublishingHouse(publishingHouse);
    }

    @GetMapping("/byPublishingHousesAtLeastOne")
    public List<Book> booksByPublishingHousesAtLeastOne(List<String> publishingHouses) {
        return service.booksByPublishingHousesAtLeastOne(publishingHouses);
    }

    @GetMapping("/byPages")
    public List<Book> booksByPages(Integer pages) {
        return service.booksByPages(pages);
    }

    @GetMapping("/byPagesRange")
    public List<Book> booksByPagesRange(Integer pagesFrom, Integer pagesTo) {
        return service.booksByPagesRange(pagesFrom, pagesTo);
    }

    @GetMapping("/byTextFragment")
    public List<Book> booksByTextFragment(String text) {
        return service.booksByTextFragment(text);
    }
}
