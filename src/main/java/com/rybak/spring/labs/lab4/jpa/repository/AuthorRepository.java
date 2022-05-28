package com.rybak.spring.labs.lab4.jpa.repository;

import com.rybak.spring.labs.lab4.jpa.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Query("select a from Author a where a.name = :name")
    List<Author> findAuthorByName(@Param("name") String name);
}
