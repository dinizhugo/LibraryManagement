package com.library.controller;

import com.library.exceptions.DevolutionNotFoundException;
import com.library.exceptions.LoanAlreadyReturned;
import com.library.exceptions.UninformedParameterException;
import com.library.model.entities.Book;
import com.library.model.entities.Devolution;
import com.library.model.entities.Loan;
import com.library.model.entities.LoanStatus;
import com.library.model.service.BookService;
import com.library.model.service.DevolutionService;
import com.library.model.service.LoanService;

import java.time.LocalDate;
import java.util.List;

public class DevolutionController {
    private final DevolutionService devolutionService = new DevolutionService();

    public void addDevolution(Loan loan, LocalDate returnDate) throws UninformedParameterException, LoanAlreadyReturned {
        if (loan == null && returnDate == null) {
            throw new UninformedParameterException();
        }

        if (loan != null && loan.getLoanStatus() == LoanStatus.RETURNED) {
            throw new LoanAlreadyReturned();
        }

        returnedBook(loan);
        double trafficTicket = calculateLateFee(returnDate, loan.getEstimatedDate());
        devolutionService.addDevolution(new Devolution(null, loan, returnDate, trafficTicket));
    }

    public void updateDevolution(Devolution devolution, Loan loan, LocalDate returnDate) throws UninformedParameterException {
        if (devolution == null && loan == null && returnDate == null) {
            throw new UninformedParameterException();
        }
        devolution.setReturnDate(returnDate);
        devolution.setLoan(loan);
        double trafficTicket = calculateLateFee(returnDate, loan.getEstimatedDate());
        devolution.setTrafficTicket(trafficTicket);
        devolutionService.updateDevolution(devolution);
    }

    public void deleteDevolution(Integer id) {
        devolutionService.deleteDevolutionById(id);
    }

    public Devolution getDevolutionById(Integer id) throws DevolutionNotFoundException {
        Devolution devolution = devolutionService.getDevolutionById(id);

        if (devolution == null) {
            throw new DevolutionNotFoundException();
        }
        return devolution;
    }

    public Devolution getDevolutionByLoan(Loan loan) throws DevolutionNotFoundException {
        Devolution devolution = devolutionService.getDevolutionByLoan(loan);

        if (devolution == null) {
            throw new DevolutionNotFoundException();
        }
        return devolution;
    }

    public List<Devolution> getDevolutions() {
        return devolutionService.getAllDevolution();
    }

    private double calculateLateFee(LocalDate returnDate, LocalDate estimatedReturnDate) throws UninformedParameterException {
        if (returnDate == null && estimatedReturnDate == null) {
            throw new UninformedParameterException();
        }
        return devolutionService.calculateLateFee(returnDate, estimatedReturnDate);
    }

    private void returnedBook(Loan loan) {
        LoanService loanService = new LoanService();
        BookService bookService = new BookService();
        Book book = loan.getBook();
        int currentAmount = book.getAmount();

        loan.setLoanStatus(LoanStatus.RETURNED);
        book.setAmount(currentAmount + 1);
        bookService.updateBook(book);
        loanService.updateLoan(loan);
    }
}
