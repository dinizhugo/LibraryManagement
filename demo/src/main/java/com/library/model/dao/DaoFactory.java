package com.library.model.dao;

import com.library.database.DB;
import com.library.model.dao.implementation.BookJDBC;
import com.library.model.dao.implementation.DevolutionJDBC;
import com.library.model.dao.implementation.LoanJDBC;
import com.library.model.dao.implementation.UserJDBC;
import com.library.model.entities.Book;
import com.library.model.entities.User;

public class DaoFactory {

    public static GenericDao<User> createUserDao() {
        return new UserJDBC(DB.getConnection());
    }

    public static GenericDao<Book> createBookDao() {
        return new BookJDBC(DB.getConnection());
    }

    public static LoanDao createLoanDao() {
        return new LoanJDBC(DB.getConnection());
    }

    public static DevolutionDao createDevolutionDao() {
        return new DevolutionJDBC(DB.getConnection());
    }
}
