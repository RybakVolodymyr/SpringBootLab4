package com.rybak.spring.labs.lab4.jpa.repository;

import com.rybak.spring.labs.lab4.jpa.entity.Author;
import com.rybak.spring.labs.lab4.jpa.entity.Book;
import com.rybak.spring.labs.lab4.jpa.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, BookRepositoryCustom {

    @Query("SELECT b FROM Book b " +
            "INNER JOIN b.authors a " +
            "WHERE a = :author ")
    List<Book> jpqlBooksByAuthor(@Param("author") Author author);

    @Query("SELECT DISTINCT b FROM Book b " +
            "INNER JOIN b.authors a " +
            "WHERE a IN :authors ")
    List<Book> jpqlBooksByAuthorsAtLeastOne(@Param("authors") List<Author> authors);

    @Query("SELECT DISTINCT b FROM Book b " +
            "INNER JOIN b.authors a " +
            "WHERE a IN :authors " +
            "GROUP BY b " +
            "HAVING COUNT(a) = CAST(:#{#authors.size()} as integer)")
    List<Book> jpqlBooksByAuthors(@Param("authors") List<Author> authors);

    @Query("SELECT b FROM Book b " +
            "INNER JOIN b.genres g " +
            "WHERE g = :genre ")
    List<Book> jpqlBooksByGenre(@Param("genre") Genre genre);

    @Query("SELECT DISTINCT b FROM Book b " +
            "INNER JOIN b.genres g " +
            "WHERE g IN :genres ")
    List<Book> jpqlBooksByGenresAtLeastOne(@Param("genres") List<Genre> genres);

    @Query("SELECT DISTINCT b FROM Book b " +
            "INNER JOIN b.genres g " +
            "WHERE g IN :genres " +
            "GROUP BY b " +
            "HAVING COUNT(a) = CAST(:#{#genres.size()} as integer)")
    List<Book> jpqlBooksByGenres(@Param("genres") List<Genre> genres);

    @Query("SELECT b FROM Book b " +
            "WHERE b.price = :price ")
    List<Book> jpqlBooksByPrice(@Param("price") Double price);

    @Query("SELECT b FROM Book b " +
            "WHERE b.price >= :priceFrom AND b.price <= :priceTo ")
    List<Book> jpqlBooksByPriceRange(@Param("priceFrom") Double priceFrom,
                                @Param("priceTo") Double priceTo);

    @Query("SELECT b FROM Book b " +
            "WHERE b.publishingHouse = :publishingHouse ")
    List<Book> jpqlBooksByPublishingHouse(@Param("publishingHouse") String publishingHouse);

    @Query("SELECT b FROM Book b " +
            "WHERE b.publishingHouse IN :publishingHouses ")
    List<Book> jpqlBooksByPublishingHousesAtLeastOne(@Param("publishingHouses") List<String> publishingHouses);


    @Query("SELECT b FROM Book b " +
            "WHERE b.pages = :pages ")
    List<Book> jpqlBooksByPages(@Param("pages") Integer pages);

    @Query("SELECT b FROM Book b " +
            "WHERE b.pages >= :pagesFrom AND b.pages <= :pagesTo ")
    List<Book> jpqlBooksByPagesRange(@Param("pagesFrom") Integer pagesFrom,
                                     @Param("pagesTo") Integer pagesTo);

    @Query("SELECT b FROM Book b " +
            "INNER JOIN b.authors a " +
            "WHERE lower(a.name) LIKE concat('%', lower(:text), '%') " +
            "OR lower(b.name) LIKE concat('%', lower(:text), '%') " +
            "OR lower(b.description) LIKE concat('%', lower(:text), '%') " +
            "OR lower(b.publishingHouse) LIKE concat('%', lower(:text), '%') ")
    List<Book> jpqlBooksByTextFragment(@Param("text") String text);
}
