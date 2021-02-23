package com.web.dao;

import com.web.madel.Books;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {


    @PersistenceContext
    private EntityManager sessionFactory;


    @Override
    @SuppressWarnings("unchecked")
    public List<Books> getAll () {
        return (List<Books>) sessionFactory.createQuery("select book from Books book order by book.id").getResultList();
    }

    @Override
    public void insert (Books books) {
        sessionFactory.persist(books);
    }

    @Override
    public Books getById (Long id) {
        return sessionFactory.find(Books.class, id);
    }

    @Override
    public void delete (Books books) {
        sessionFactory.remove(books);
    }

    @Override
    public void update (Books books) {
        sessionFactory.merge(books);
    }
}
