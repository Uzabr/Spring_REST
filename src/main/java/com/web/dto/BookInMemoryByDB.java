package com.web.dto;

import java.util.ArrayList;
import java.util.List;

public enum BookInMemoryByDB {

    INSTANCE;
    private static List<BookDto> bookDtoList = new ArrayList<>();
    private static Long lastId = 0L;

    public Long getId() {
        return ++lastId;
    }

    public void addBook(BookDto bookDto) {

        bookDto.setId(getId());
        bookDtoList.add(bookDto);
    }

    public List<BookDto> findAll() {
        return bookDtoList;
    }


    public BookDto fidById(Long id) {
    for (BookDto bookDto : bookDtoList) {

      if (bookDto.getId() == id) {
          return bookDto;
      }
    }
        return null;
    }

    public void remove(Long id) {
        BookDto dto = null;

    for (BookDto bookDto : bookDtoList) {
      if (bookDto.getId() == id) {
          dto = bookDto;
          break;
      }
    }
    if (dto == null ) bookDtoList.remove(dto);
    }

    public void edit(BookDto bookDto) {
    for (BookDto dto : bookDtoList) {
      if (dto.getId() == bookDto.getId()) {
          dto.setName(bookDto.getName());
          dto.setCity(bookDto.getCity());
      }

    }
    }
}
