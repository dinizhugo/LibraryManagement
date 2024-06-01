package com.library.model.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Devolution {
    private Integer id;
    private Integer loanId;
    private LocalDate returnDate;
    private Double trafficTicket;

    public Devolution() {}

    public Devolution(Integer id, Integer loanId, LocalDate returnDate, Double trafficTicket) {
        this.id = id;
        this.loanId = loanId;
        this.returnDate = returnDate;
        this.trafficTicket = trafficTicket;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Double getTrafficTicket() {
        return trafficTicket;
    }

    public void setTrafficTicket(Double trafficTicket) {
        this.trafficTicket = trafficTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Devolution that = (Devolution) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
