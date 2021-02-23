package com.web.component;


import com.web.dto.BookDto;
import com.web.madel.Books;
import com.web.madel.Shipping;
import org.springframework.stereotype.Component;

import java.awt.print.Book;

@Component
public class BookMapper {

    public Books mapDtoToEntity(BookDto bookDto) {
        Books books = new Books();
        Shipping shipping = new Shipping();

    if (bookDto.getId() != null) books.setId(bookDto.getId());
    if (bookDto.getName() != null) books.setName(books.getName());
    if (bookDto.getCity() != null) {
        shipping.setCity(bookDto.getCity());
        books.setShipping(shipping);
    }
    return books;
    }

    public BookDto mapEntityToDto(Books books) {
        BookDto bookDto =  new BookDto();
    if (books.getId() != null) bookDto.setId(books.getId());
    if (books.getName() != null) bookDto.setName(books.getName());
    if (books.getShipping() != null) {
        if (books.getShipping().getCity() != null) {
        bookDto.setCity(books.getShipping().getCity());
        }
    }
    return bookDto;
    }
}
