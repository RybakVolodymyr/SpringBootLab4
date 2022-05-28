package com.rybak.spring.labs.lab4.check.data;

import com.rybak.spring.labs.lab4.jdbc.dao.AuthorDao;
import com.rybak.spring.labs.lab4.jdbc.dao.BookDao;
import com.rybak.spring.labs.lab4.jdbc.dao.GenreDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.assertEquals;

@DataJdbcTest
public class BookJdbcTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void getBookTest() {
        BookDao bookDao = new BookDao();
        bookDao.setJdbcTemplate(jdbcTemplate);
        assertEquals(bookDao.getCount(), 5);
    }

    @Test
    void getAuthorTest() {
        AuthorDao authorDao = new AuthorDao();
        authorDao.setJdbcTemplate(jdbcTemplate);
        assertEquals(authorDao.getCount(), 5);
    }

    @Test
    void getGenreTest() {
        GenreDao genreDao = new GenreDao();
        genreDao.setJdbcTemplate(jdbcTemplate);
        assertEquals(genreDao.getCount(), 5);
    }
}
