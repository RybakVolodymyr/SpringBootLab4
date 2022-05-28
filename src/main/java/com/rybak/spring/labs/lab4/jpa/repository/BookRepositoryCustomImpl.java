package com.rybak.spring.labs.lab4.jpa.repository;


import com.rybak.spring.labs.lab4.jpa.entity.Author;
import com.rybak.spring.labs.lab4.jpa.entity.Book;
import com.rybak.spring.labs.lab4.jpa.entity.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    public List<Book> criteriaBooksByAuthor(UUID authorId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        Join<Book, Author> bookAuthor = book.join("authors", JoinType.INNER);
        cq.select(book).where(cb.equal(bookAuthor.get("id"), authorId));

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Book> criteriaBooksByAuthorsAtLeastOne(List<UUID> authorIds) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        Join<Book, Author> bookAuthor = book.join("authors", JoinType.INNER);
        cq.select(book).distinct(true).where(bookAuthor.get("id").in(authorIds));

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Book> criteriaBooksByAuthors(List<UUID> authorIds) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        Join<Book, Author> bookAuthor = book.join("authors", JoinType.INNER);
        cq.select(book).distinct(true)
                .where(bookAuthor.get("id").in(authorIds))
                .groupBy(book)
                .having(cb.equal(cb.count(bookAuthor), authorIds.size()));

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Book> criteriaBooksByGenre(UUID genreId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        Join<Book, Genre> bookGenre = book.join("genres", JoinType.INNER);
        cq.select(book).where(cb.equal(bookGenre.get("id"), genreId));

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Book> criteriaBooksByGenresAtLeastOne(List<UUID> genreIds) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        Join<Book, Genre> bookGenre = book.join("genres", JoinType.INNER);
        cq.select(book).distinct(true).where(bookGenre.get("id").in(genreIds));

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Book> criteriaBooksByGenres(List<UUID> genreIds) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        Join<Book, Genre> bookGenre = book.join("genres", JoinType.INNER);
        cq.select(book).distinct(true)
                .where(bookGenre.get("id").in(genreIds))
                .groupBy(book)
                .having(cb.equal(cb.count(bookGenre), genreIds.size()));

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Book> criteriaBooksByPrice(Double price) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        cq.select(book).where(cb.equal(book.get("price"), price));

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Book> criteriaBooksByPriceRange(Double priceFrom, Double priceTo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        Predicate greater = cb.greaterThanOrEqualTo(book.get("price"), priceFrom);
        Predicate less = cb.lessThanOrEqualTo(book.get("price"), priceTo);
        Predicate full = cb.and(greater, less);
        cq.select(book).where(full);

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Book> criteriaBooksByPublishingHouse(String publishingHouse) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        cq.select(book).where(cb.equal(book.get("publishingHouse"), publishingHouse));

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Book> criteriaBooksByPublishingHousesAtLeastOne(List<String> publishingHouses) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        cq.select(book).where(book.get("publishingHouse").in(publishingHouses));

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Book> criteriaBooksByPages(Integer pages) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        cq.select(book).where(cb.equal(book.get("pages"), pages));

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Book> criteriaBooksByPagesRange(Integer pagesFrom, Integer pagesTo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        Predicate greater = cb.greaterThanOrEqualTo(book.get("pages"), pagesFrom);
        Predicate less = cb.lessThanOrEqualTo(book.get("pages"), pagesTo);
        Predicate full = cb.and(greater, less);
        cq.select(book).where(full);

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Book> criteriaBooksByTextFragment(String text) {
        String textParam = '%' + text.toLowerCase() + '%';
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        Join<Book, Author> bookAuthor = book.join("authors", JoinType.INNER);

        Predicate authorPr = cb.like(cb.lower(bookAuthor.get("name")), textParam);
        Predicate namePr = cb.like(cb.lower(book.get("name")), textParam);
        Predicate descriptionPr = cb.like(cb.lower(book.get("description")), textParam);
        Predicate publishingHousePr = cb.like(cb.lower(book.get("publishingHouse")), textParam);
        Predicate predicate = cb.or(authorPr, namePr, descriptionPr, publishingHousePr);
        cq.select(book).distinct(true)
                .where(predicate);

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }
}
