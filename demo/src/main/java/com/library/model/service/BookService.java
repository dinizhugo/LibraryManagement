package com.library.model.service;

import com.library.model.dao.DaoFactory;
import com.library.model.dao.GenericDao;
import com.library.model.entities.Book;

import java.time.LocalDate;
import java.util.List;

public class BookService {
    private final GenericDao<Book> bookDao;

    public BookService() {
        this.bookDao =  DaoFactory.createBookDao();
    }

    public void addBook(String title, String author, String publishingCompany, String category, int amount, LocalDate publicationDate) {
        bookDao.insert(new Book(null, title, author, publishingCompany, category, amount,publicationDate));
    }

    public void updateBook(Book book, String title, String author, String publishingCompany, String category, int amount, LocalDate publicationDate) {
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishingCompany(publishingCompany);
        book.setCategory(category);
        book.setAmount(amount);
        book.setPublicationDate(publicationDate);
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
