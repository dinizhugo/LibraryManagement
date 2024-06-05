package com.library.controller;

import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.UninformedParameterException;
import com.library.model.entities.Book;
import com.library.model.service.BookService;

import java.time.LocalDate;
import java.util.List;

public class BookController {
    private final BookService bookService = new BookService();

    public void addBook(String title, String author, String publishingCompany, String category, int amount, LocalDate publicationDate) throws UninformedParameterException {
        if (title == null && author == null && category == null && amount < 1 && publicationDate == null) {
            throw new UninformedParameterException();
        }
        bookService.addBook(new Book(null, title, author, publishingCompany, category, amount,publicationDate));
    }

    public void updateBook(Book book, String title, String author, String publishingCompany, String category, int amount, LocalDate publicationDate) {
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishingCompany(publishingCompany);
        book.setCategory(category);
        book.setAmount(amount);
        book.setPublicationDate(publicationDate);
        bookService.updateBook(book);
    }

    public void deleteBook(Integer id) {
        bookService.deleteBookById(id);
    }

    public Book getBook(Integer  id) throws BookNotFoundException {
        Book book = bookService.getBookById(id);

        if (book == null) {
            throw new BookNotFoundException();
        }
        return book;
    }

    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }
}
