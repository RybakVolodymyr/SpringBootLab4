package com.rybak.spring.labs.lab4.jpa.repository;


import com.rybak.spring.labs.lab4.jpa.entity.Book;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface BookRepositoryCustom {

    List<Book> criteriaBooksByAuthor(UUID authorId);

    List<Book> criteriaBooksByAuthors(List<UUID> authorIds);

    List<Book> criteriaBooksByAuthorsAtLeastOne(List<UUID> authorIds);

    List<Book> criteriaBooksByGenre(UUID genreId);

    List<Book> criteriaBooksByGenresAtLeastOne(List<UUID> genreIds);

    List<Book> criteriaBooksByGenres(List<UUID> genreIds);

    List<Book> criteriaBooksByPrice(Double price);

    List<Book> criteriaBooksByPriceRange(Double priceFrom, Double priceTo);

    List<Book> criteriaBooksByPublishingHouse(String publishingHouse);

    List<Book> criteriaBooksByPublishingHousesAtLeastOne(List<String> publishingHouses);

    List<Book> criteriaBooksByPages(Integer pages);

    List<Book> criteriaBooksByPagesRange(Integer pagesFrom, Integer pagesTo);

    List<Book> criteriaBooksByTextFragment(String text);
}
