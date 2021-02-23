package com.web.service;

import com.web.dto.BookDto;
import com.web.srvice.BookService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testGetAll() {
        Assertions.assertEquals(0L, bookService.findAll().size());
    }

    @Test
    public void testCreate(){
        BookDto bookDto = new BookDto();
        bookDto.setName("Java Book");
        bookDto.setCity("UK");
        bookService.create(bookDto);

        Assertions.assertEquals(1L, bookService.findAll().size());
    }

    @Test
    public void testFidById() {
        BookDto bookDto = new BookDto();
        bookDto.setName("Java Book");
        bookDto.setCity("UK");
        bookService.create(bookDto);

        List<BookDto> list = bookService.findAll();
        BookDto bookDto1 = list.get(0);
        BookDto bookDto2 = bookService.findById(bookDto1.getId());

        Assertions.assertEquals("Java Book", bookDto2.getName());
        Assertions.assertEquals("UK", bookDto2.getCity());
    }

    @Test
    public void testRemove() {
        BookDto bookDto = new BookDto();
        bookDto.setName("Java Book");
        bookDto.setCity("UK");
        bookService.create(bookDto);

        Assertions.assertEquals(1L, bookService.findAll().size());

        List<BookDto> list = bookService.findAll();
        BookDto bookDto1 = list.get(0);
        bookService.remove(bookDto1.getId());

        Assertions.assertEquals(0L, bookService.findAll().size());

    }

    @Test
    public void testEdit() {
        BookDto bookDto = new BookDto();
        bookDto.setName("Java SE Book");
        bookDto.setCity("IND");
        bookService.create(bookDto);

        Assertions.assertEquals(1L, bookService.findAll().size());

        List<BookDto> list = bookService.findAll();
        BookDto bookDto1 = list.get(0);
        bookDto.setCity("CAN");
        bookService.edit(bookDto1);
        List<BookDto> bookDtos = bookService.findAll();
        BookDto bookDto2 = bookDtos.get(0);


        Assertions.assertEquals("CAN", bookDto2.getCity());
    }


}
