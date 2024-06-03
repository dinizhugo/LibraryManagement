package com.library.model.dao;

import com.library.model.entities.Devolution;
import com.library.model.entities.Loan;

import java.util.List;

public interface DevolutionDao {
    void insert(Devolution obj);
    void update(Devolution obj);
    void deleteById(Integer id);
    Devolution findById(Integer id);
    Devolution findByLoan(Loan loan);
    List<Devolution> findAll();
}
