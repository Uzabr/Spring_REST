package com.web.dao;

import com.web.madel.Books;

import java.util.List;

public interface BookDao {

    void insert(Books books);
    void delete(Books books);
    void update(Books books);
    Books getById(Long id);
    List<Books> getAll();
}
