package com.library.controller;

import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.UnableToDeleteBook;
import com.library.exceptions.UninformedParameterException;
import com.library.exceptions.UserNotFoundException;
import com.library.model.entities.Book;
import com.library.model.entities.Loan;
import com.library.model.entities.LoanStatus;
import com.library.model.entities.User;
import com.library.model.service.BookService;
import com.library.model.service.LoanService;

import java.time.LocalDate;
import java.util.List;

public class BookController {
    private final BookService bookService = new BookService();

    public void addBook(String title, String author, String publishingCompany, String category, int amount, LocalDate publicationDate) throws UninformedParameterException {
        if (title == null || author == null || amount < 1 || publicationDate == null) {
            throw new UninformedParameterException();
        }
        bookService.addBook(new Book(null, title, author, publishingCompany, category, amount,publicationDate));
    }

    public void updateBook(Book book, String title, String author, String publishingCompany, String category, int amount, LocalDate publicationDate) throws UninformedParameterException {
        if (title == null || author == null ||  amount < 1 || publicationDate == null) {
            throw new UninformedParameterException();
        }
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishingCompany(publishingCompany);
        book.setCategory(category);
        book.setAmount(amount);
        book.setPublicationDate(publicationDate);
        bookService.updateBook(book);
    }

    public void deleteBook(Integer id) throws BookNotFoundException, UnableToDeleteBook {
        if (thisBookHasALoan(id)) {
            throw new UnableToDeleteBook();
        }
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

    private boolean thisBookHasALoan(Integer id) throws BookNotFoundException {
        Book book = getBook(id);

        if (book != null) {
            LoanService loanService = new LoanService();
            List<Loan> loans = loanService.getLoanByBook(book).stream().filter(loan -> loan.getLoanStatus() == LoanStatus.NOT_RETURNED).toList();

            return !loans.isEmpty();
        }
        return false;
    }
}
