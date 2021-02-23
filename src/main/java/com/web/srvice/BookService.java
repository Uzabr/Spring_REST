package com.web.srvice;

import com.web.dto.BookDto;

import java.util.List;

public interface BookService {
    void create(BookDto bookDto);
    void remove(Long id);
    void edit(BookDto bookDto);
    BookDto findById(Long id);
    List<BookDto> findAll();
}
