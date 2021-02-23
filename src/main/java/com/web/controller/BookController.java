package com.web.controller;

import com.web.dto.BookDto;
import com.web.srvice.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping(value = "/onetooneunidiractional")
@Transactional
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/findAll")
    public @ResponseBody List<BookDto> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/findById/{bookId}")
    public @ResponseBody BookDto findById(@PathVariable("bookId") Long bookId) {
        return bookService.findById(bookId);
    }

    @PostMapping("/create")
    @ResponseBody
    public void create(@RequestBody BookDto bookDto) {
        bookService.create(bookDto);
    }

    @PostMapping("/remove/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("bookId") Long bookId){
        bookService.remove(bookId);
    }

    @PostMapping("/edit")
    @ResponseBody
    public void edit(@RequestBody BookDto bookDto) {
        bookService.edit(bookDto);
    }
}
