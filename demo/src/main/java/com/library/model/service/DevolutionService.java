package com.library.model.service;

import com.library.model.dao.DaoFactory;
import com.library.model.dao.DevolutionDao;
import com.library.model.entities.Devolution;
import com.library.model.entities.Loan;

import java.time.LocalDate;
import java.util.List;

public class DevolutionService {
    private DevolutionDao devolutionDao = DaoFactory.createDevolutionDao();

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

}
