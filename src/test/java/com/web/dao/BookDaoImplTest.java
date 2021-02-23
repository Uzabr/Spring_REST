package com.web.dao;

import com.web.dao.BookDao;
import com.web.madel.Books;
import com.web.madel.Shipping;
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
public class BookDaoImplTest {

    @Autowired
    private BookDao bookDao;
    @Test
    public void testGetAll() {

        Assertions.assertEquals(0L, bookDao.getAll().size());
    }

    @Test
    public void testInsert() {
        Books books1 = new Books();
        books1.setName("Java Book");
        Shipping shipping1 = new Shipping();
        shipping1.setCity("UK");
        books1.setShipping(shipping1);
        bookDao.insert(books1);


        Books books2 = new Books();
        books2.setName("Java SE Book");
        Shipping shipping2 = new Shipping();
        shipping2.setCity("IN");
        books2.setShipping(shipping2);
        bookDao.insert(books2);

    Assertions.assertEquals(2L, bookDao.getAll().size());
    }

    @Test
    public void testGetById() {
        Books books1 = new Books();
        books1.setName("Java Book");
        Shipping shipping1 = new Shipping();
        shipping1.setCity("UK");
        books1.setShipping(shipping1);
        bookDao.insert(books1);

        List<Books> list = bookDao.getAll();
        Books books = list.get(0);
        Books tempBook = bookDao.getById(books.getId());

    Assertions.assertEquals(tempBook.getName(), books.getName());
    }

    @Test
    public void testDelete() {
        Books books1  = new Books();
        books1.setName("Java Book");
        Shipping shipping1 = new Shipping();
        shipping1.setCity("UK");
        books1.setShipping(shipping1);
        bookDao.insert(books1);

        Books books2 = new Books();
        books2.setName("Java SE Book");
        Shipping shipping2 = new Shipping();
        shipping2.setCity("IN");
        books2.setShipping(shipping2);
        bookDao.insert(books2);

        Assertions.assertEquals(2L, bookDao.getAll().size());

        bookDao.delete(books2);
        Assertions.assertEquals(1L, bookDao.getAll().size());

    }

    @Test
    public void testUdate() {
        Books books1 =new Books();
        books1.setName("Java Book");
        Shipping shipping1 = new Shipping();
        shipping1.setCity("UK");
        books1.setShipping(shipping1);
        bookDao.insert(books1);

        Assertions.assertEquals(1L, bookDao.getAll().size());

        List<Books> list = bookDao.getAll();
        Books books2 = list .get(0);
        Shipping shipping2 = books2.getShipping();
        shipping2.setCity("IND");
        bookDao.update(books2);

        List<Books> list1 = bookDao.getAll();
        Books books3 = list1.get(0);
        Assertions.assertEquals("IND", books3.getShipping().getCity());
    }
}
