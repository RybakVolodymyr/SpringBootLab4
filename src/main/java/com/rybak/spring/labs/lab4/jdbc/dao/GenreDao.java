package com.rybak.spring.labs.lab4.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class GenreDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM genres", Integer.class);
    }
}
