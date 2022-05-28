package com.rybak.spring.labs.lab4.check.data.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class BookDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM books", Integer.class);
    }
}
