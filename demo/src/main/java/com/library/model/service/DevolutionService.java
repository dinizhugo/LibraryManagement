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
    private static final double DAILY_LATE_FEE = 1.50;

    public DevolutionService() {
        this.devolutionDao = DaoFactory.createDevolutionDao();
    }

    public void addDevolution(Devolution devolution) {
        devolutionDao.insert(devolution);
    }

    public void updateDevolution(Devolution devolution) {
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

    public double calculateLateFee(LocalDate returnDate, LocalDate estimatedReturnDate) {
        if (returnDate.isAfter(estimatedReturnDate)) {
            long daysLate = ChronoUnit.DAYS.between(estimatedReturnDate, returnDate);
            return DAILY_LATE_FEE * daysLate;
        }
        return  0.0;
    }

}
