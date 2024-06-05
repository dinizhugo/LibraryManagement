package com.library.controller;

import com.library.exceptions.DevolutionNotFoundException;
import com.library.exceptions.UninformedParameterException;
import com.library.model.entities.Devolution;
import com.library.model.entities.Loan;
import com.library.model.service.DevolutionService;

import java.time.LocalDate;
import java.util.List;

public class DevolutionController {
    private final DevolutionService devolutionService = new DevolutionService();

    public void addDevolution(Loan loan, LocalDate returnDate, Double trafficTicket) throws UninformedParameterException {
        if (loan == null && returnDate == null) {
            throw new UninformedParameterException();
        }
        devolutionService.addDevolution(new Devolution(null, loan, returnDate, trafficTicket));
    }

    public void updateDevolution(Devolution devolution, Loan loan, LocalDate returnDate, Double trafficTicket) {
        devolution.setReturnDate(returnDate);
        devolution.setLoan(loan);
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

    public double calculateLateFee(Devolution devolution) throws UninformedParameterException {
        if (devolution == null) {
            throw new UninformedParameterException();
        }
        return devolutionService.calculateLateFee(devolution);
    }
}
