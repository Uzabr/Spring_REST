package com.web.model;

import com.web.madel.Books;
import com.web.madel.Shipping;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookTest {

    @Autowired
    private EntityManager sessionFactory;

    @Test
    public void testCRUD() {
        Books book1 = new Books();
        book1.setName("Java7");
        Shipping shipping1 = new Shipping();
        shipping1.setCity("US");
        book1.setShipping(shipping1);
        sessionFactory.persist(book1);

        Books book2 = new Books();
        book2.setName("EJB 3.0");
        Shipping shipping2 = new Shipping();
        shipping2.setCity("CAN");
        book2.setShipping(shipping2);
        sessionFactory.persist(book2);

        book1.setName("JEE");
        shipping1 = book1.getShipping();
        shipping1.setCity("UK");
        sessionFactory.merge(book1);

        List<Books> books = sessionFactory.createQuery("select books from Books books").getResultList();

        Assertions.assertEquals(2L, books.size());

        sessionFactory.remove(book2);

        books = sessionFactory.createQuery("select books from Books books").getResultList();

        Assertions.assertEquals(1L, books.size());
    }


}
