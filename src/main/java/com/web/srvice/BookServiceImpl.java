package com.web.srvice;

import com.web.component.BookMapper;
import com.web.dao.BookDao;
import com.web.dto.BookDto;
import com.web.madel.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookMapper mapper;

    @Override
    public List<BookDto> findAll () {
        List<Books> list = bookDao.getAll();

        List<BookDto> bookDto = new ArrayList<>();

    for (Books books : list) {
      bookDto.add(mapper.mapEntityToDto(books));
    }
    return bookDto;
    }

    @Override
    public void create (BookDto bookDto) {
        bookDao.insert(mapper.mapDtoToEntity(bookDto));
    }

    @Override
    public BookDto findById (Long id) {
        Books books = bookDao.getById(id);
        if (books != null){
            return mapper.mapEntityToDto(books);
        }
        return null;
    }

    @Override
    public void remove (Long id) {
    Books books = bookDao.getById(id);
    bookDao.delete(books);

    }

    @Override
    public void edit (BookDto bookDto) {
    bookDao.update(mapper.mapDtoToEntity(bookDto));
    }

}
