package com.library.model.service;

import com.library.model.dao.DaoFactory;
import com.library.model.dao.DevolutionDao;
import com.library.model.entities.Devolution;
import com.library.model.entities.Loan;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DevolutionService {
    private final DevolutionDao devolutionDao;
    private static final double DAILY_LATE_FEE = 1.5;

    public DevolutionService() {
        this.devolutionDao = DaoFactory.createDevolutionDao();
    }

    public void addDevolution(Loan loan, LocalDate returnDate, Double trafficTicket) {
        devolutionDao.insert(new Devolution(null, loan, returnDate, trafficTicket));
    }

    public void updateDevolution(Devolution devolution, Loan loan, LocalDate returnDate, Double trafficTicket) {
        devolution.setReturnDate(returnDate);
        devolution.setLoan(loan);
        devolution.setTrafficTicket(trafficTicket);
        devolutionDao.update(devolution);
    }

    public void deleteDevolutionById(Integer id) {
        devolutionDao.deleteById(id);
    }

    public Devolution getDevolutionById(Integer id) {
        return  devolutionDao.findById(id);
    }

    public Devolution getDevolutionByLoan(Loan loan) {
        return devolutionDao.findByLoan(loan);
    }

    public List<Devolution> getAllDevolution() {
        return devolutionDao.findAll();
    }

    public double calculateLateFee(Devolution devolution) {
        LocalDate returnDate = devolution.getReturnDate();
        LocalDate estimatedReturnDate = devolution.getLoan().getEstimatedDate();

        if (returnDate.isAfter(estimatedReturnDate)) {
            long daysLate = ChronoUnit.DAYS.between(estimatedReturnDate, returnDate);
            return DAILY_LATE_FEE * daysLate;
        }
        return  0.0;
    }

}
