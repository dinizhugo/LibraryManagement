package com.library.controller;

import com.library.exceptions.InsufficientBooksExceptions;
import com.library.exceptions.LoanNotFoundException;
import com.library.exceptions.UninformedParameterException;
import com.library.model.entities.Book;
import com.library.model.entities.Loan;
import com.library.model.entities.User;
import com.library.model.service.LoanService;

import java.time.LocalDate;
import java.util.List;

public class LoanController {
    private final LoanService loanService = new LoanService();

    public void createNewLoan(User user, Book book, LocalDate loanDate, LocalDate estimatedDate) throws UninformedParameterException, InsufficientBooksExceptions {
        if (user == null && book == null && estimatedDate == null) {
            throw new UninformedParameterException();
        }

        if (book != null && book.getAmount() < 0) {
            throw new InsufficientBooksExceptions();
        }

        loanService.createNewLoan(new Loan(null, user, book, loanDate, estimatedDate));
    }

    public void updateLoan(Loan loan, User user, Book book, LocalDate loanDate, LocalDate estimatedDate) {
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(loanDate);
        loan.setEstimatedDate(estimatedDate);
        loanService.updateLoan(loan);
    }

    public void deleteLoan(Integer id) {
        loanService.deleteLoanById(id);
    }

    public Loan getLoan(Integer id) throws LoanNotFoundException {
        Loan loan = loanService.getLoanById(id);

        if (loan == null) {
            throw new LoanNotFoundException();
        }
        return loan;
    }

    public List<Loan> getLoans() {
        return loanService.getAllLoans();
    }

    public List<Loan> getLoansByUser(User user) throws LoanNotFoundException {
        List<Loan> loans = loanService.getLoanByUser(user);

        if (loans == null) {
            throw new LoanNotFoundException();
        }
        return loans;
    }

    public List<Loan> getLoansByBook(Book book) throws LoanNotFoundException {
        List<Loan> loans = loanService.getLoanByBook(book);

        if (loans == null) {
            throw new LoanNotFoundException();
        }
        return loans;
    }
}