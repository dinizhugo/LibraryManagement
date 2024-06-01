package com.library.model.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {
    private Integer id;
    private Integer idUser;
    private Integer idBook;
    private LocalDate loanDate;
    private LocalDate estimatedDate;

    public Loan() {}

    public Loan(Integer id, Integer idUser, Integer idBook, LocalDate loanDate, LocalDate estimatedDate) {
        this.id = id;
        this.idUser = idUser;
        this.idBook = idBook;
        this.loanDate = loanDate;
        this.estimatedDate = estimatedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(LocalDate estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
