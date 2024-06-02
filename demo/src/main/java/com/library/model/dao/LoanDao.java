package com.library.model.dao;

import com.library.model.entities.Book;
import com.library.model.entities.Loan;
import com.library.model.entities.User;

import java.util.List;

public interface LoanDao {
    void insert(Loan obj);
    void update(Loan obj);
    void deleteById(Integer id);
    Loan findById(Integer id);
    List<Loan> findByUser(User user);
    List<Loan> findByBook(Book book);
    List<Loan> findAll();
}
