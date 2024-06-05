package com.library.model.service;

import com.library.model.dao.DaoFactory;
import com.library.model.dao.GenericDao;
import com.library.model.entities.Book;

import java.util.List;

public class BookService {
    private final GenericDao<Book> bookDao;

    public BookService() {
        this.bookDao =  DaoFactory.createBookDao();
    }

    public void addBook(Book book) {
        bookDao.insert(book);
    }

    public void updateBook(Book book) {
        bookDao.update(book);
    }

    public void deleteBookById(Integer id) {
        bookDao.deleteById(id);
    }

    public Book getBookById(Integer id) {
        return bookDao.findById(id);
    }

    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

}
