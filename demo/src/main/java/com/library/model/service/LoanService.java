package com.library.model.service;

import com.library.model.dao.DaoFactory;
import com.library.model.dao.LoanDao;
import com.library.model.entities.Book;
import com.library.model.entities.Loan;
import com.library.model.entities.User;

import java.time.LocalDate;
import java.util.List;

public class LoanService {
    private final LoanDao loanDao;

    public LoanService() {
        this.loanDao = DaoFactory.createLoanDao();
    }

    public void createNewLoan(User user, Book book, LocalDate loanDate, LocalDate estimatedDate) {
        loanDao.insert(new Loan(null, user, book, loanDate, estimatedDate));
    }

    public void updateLoan(Loan loan, User user, Book book, LocalDate loanDate, LocalDate estimatedDate) {
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(loanDate);
        loan.setEstimatedDate(estimatedDate);
        loanDao.update(loan);
    }

    public void deleteLoanById(Integer id) {
        loanDao.deleteById(id);
    }

    public Loan getLoanById(Integer id) {
        return  loanDao.findById(id);
    }

    public List<Loan> getLoanByUser(User user) {
        return loanDao.findByUser(user);
    }

    public List<Loan> getLoanByBook(Book book) {
        return loanDao.findByBook(book);
    }

    public List<Loan> getAllLoans() {
        return loanDao.findAll();
    }
}
