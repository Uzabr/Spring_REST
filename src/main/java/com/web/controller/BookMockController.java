package com.web.controller;

import com.web.dto.BookInMemoryByDB;
import com.web.dto.BookDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/onetooneunidiractional/mokc")
public class BookMockController {

    @PostMapping(value = "/create")
    @ResponseBody
    public void create(@RequestBody BookDto bookDto) {

        BookInMemoryByDB.INSTANCE.addBook(bookDto);
    }

    @GetMapping(value = "/findAll")
    public @ResponseBody List<BookDto> findAll() {
        return BookInMemoryByDB.INSTANCE.findAll();
    }

    @RequestMapping(value = "/findById{bookid}",  method = RequestMethod.GET)
    public @ResponseBody BookDto findById(@PathVariable("bookid") Long bookId) {
        return BookInMemoryByDB.INSTANCE.fidById(bookId);
    }

    @PostMapping(value = "/remove{bookId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("bookId") Long id) {
        BookInMemoryByDB.INSTANCE.remove(id);
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public void edit(@RequestBody BookDto bookDto) {
        BookInMemoryByDB.INSTANCE.edit(bookDto);
    }
}
