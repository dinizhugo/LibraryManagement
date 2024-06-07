package com.library.model.dao.implementation;

import com.library.database.DB;
import com.library.database.DbException;
import com.library.model.dao.LoanDao;
import com.library.model.entities.Book;
import com.library.model.entities.Loan;
import com.library.model.entities.LoanStatus;
import com.library.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanJDBC implements LoanDao {

    private final Connection connection;

    public LoanJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Loan obj) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO loans " +
                            "(id_user, id_book, loan_date, estimated_date, status) " +
                            "VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, obj.getUser().getId());
            preparedStatement.setInt(2, obj.getBook().getId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(obj.getLoanDate()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(obj.getEstimatedDate()));
            preparedStatement.setString(5, obj.getLoanStatus().toString());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        obj.setId(resultSet.getInt(1));
                    }
                }
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }


    @Override
    public void update(Loan obj) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE loans " +
                            "SET id_user = ?, id_book = ?, loan_date = ?, estimated_date = ?, status = ?" +
                            "WHERE id = ?");
            preparedStatement.setInt(1, obj.getUser().getId());
            preparedStatement.setInt(2, obj.getBook().getId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(obj.getLoanDate()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(obj.getEstimatedDate()));
            preparedStatement.setString(5, obj.getLoanStatus().toString());
            preparedStatement.setInt(6, obj.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }


    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM loans " +
                            "WHERE id = ?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }


    @Override
    public Loan findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT loans.*, users.name as user_name, users.email as user_email, users.phone as user_phone, users.address as user_address, " +
                            "books.titulo as book_title, books.autor as book_author, books.editora as book_publishingCompany, books.quantidade as book_amount, books.categoria as book_category, books.data_publicacao as book_publicationDate " +
                            "FROM loans " +
                            "INNER JOIN users ON loans.id_user = users.id " +
                            "INNER JOIN books ON loans.id_book = books.id " +
                            "WHERE loans.id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                LoanStatus loanStatus = LoanStatus.valueOf(resultSet.getString("status"));
                User user = instantiateUser(resultSet);
                Book book = instantiateBook(resultSet);
                return instantiateLoan(resultSet, user, book, loanStatus);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }


    @Override
    public List<Loan> findByUser(User user) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT loans.*, users.name as user_name, users.email as user_email, users.phone as user_phone, users.address as user_address, " +
                            "books.titulo as book_title, books.autor as book_author, books.editora as book_publishingCompany, books.quantidade as book_amount, books.categoria as book_category, books.data_publicacao as book_publicationDate " +
                            "FROM loans " +
                            "INNER JOIN users ON loans.id_user = users.id " +
                            "INNER JOIN books ON loans.id_book = books.id " +
                            "WHERE loans.id_user = ?");
            preparedStatement.setInt(1, user.getId());
            resultSet = preparedStatement.executeQuery();

            List<Loan> loans = new ArrayList<>();
            while (resultSet.next()) {
                LoanStatus loanStatus = LoanStatus.valueOf(resultSet.getString("status"));
                Book book = instantiateBook(resultSet);
                loans.add(instantiateLoan(resultSet, user, book, loanStatus));
            }
            return loans;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }


    @Override
    public List<Loan> findByBook(Book book) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT loans.*, users.name as user_name, users.email as user_email, users.phone as user_phone, users.address as user_address, " +
                            "books.titulo as book_title, books.autor as book_author, books.editora as book_publishingCompany, books.quantidade as book_amount, books.categoria as book_category, books.data_publicacao as book_publicationDate " +
                            "FROM loans " +
                            "INNER JOIN users ON loans.id_user = users.id " +
                            "INNER JOIN books ON loans.id_book = books.id " +
                            "WHERE loans.id_book = ?");
            preparedStatement.setInt(1, book.getId());
            resultSet = preparedStatement.executeQuery();

            List<Loan> loans = new ArrayList<>();
            while (resultSet.next()) {
                User user = instantiateUser(resultSet);
                LoanStatus loanStatus = LoanStatus.valueOf(resultSet.getString("status"));
                loans.add(instantiateLoan(resultSet, user, book, loanStatus));
            }
            return loans;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }


    @Override
    public List<Loan> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT loans.*, users.name as user_name, users.email as user_email, users.phone as user_phone, users.address as user_address, " +
                            "books.titulo as book_title, books.autor as book_author, books.editora as book_publishingCompany, books.quantidade as book_amount, books.categoria as book_category, books.data_publicacao as book_publicationDate " +
                            "FROM loans " +
                            "INNER JOIN users ON loans.id_user = users.id " +
                            "INNER JOIN books ON loans.id_book = books.id " +
                            "ORDER BY loans.status");
            resultSet = preparedStatement.executeQuery();

            List<Loan> loans = new ArrayList<>();

            while (resultSet.next()) {
                LoanStatus loanStatus = LoanStatus.valueOf(resultSet.getString("status"));
                User user = instantiateUser(resultSet);
                Book book = instantiateBook(resultSet);
                Loan loan = instantiateLoan(resultSet,user, book, loanStatus);
                loans.add(loan);
            }
            return loans;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    private User instantiateUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id_user"));
        user.setName(resultSet.getString("user_name"));
        user.setEmail(resultSet.getString("user_email"));
        user.setPhone(resultSet.getString("user_phone"));
        user.setAddress(resultSet.getString("user_address"));
        return user;
    }

    private Book instantiateBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id_book"));
        book.setTitle(resultSet.getString("book_title"));
        book.setAuthor(resultSet.getString("book_author"));
        book.setPublishingCompany(resultSet.getString("book_publishingCompany"));
        book.setAmount(resultSet.getInt("book_amount"));
        book.setCategory(resultSet.getString("book_category"));
        book.setPublicationDate(resultSet.getDate("book_publicationDate").toLocalDate());
        return book;
    }

    private Loan instantiateLoan(ResultSet resultSet, User user, Book book, LoanStatus loanStatus) throws SQLException {
        Loan currentLoan = new Loan();
        currentLoan.setId(resultSet.getInt("id"));
        currentLoan.setUser(user);
        currentLoan.setBook(book);
        currentLoan.setLoanDate(resultSet.getDate("loan_date").toLocalDate());
        currentLoan.setEstimatedDate(resultSet.getDate("estimated_date").toLocalDate());
        currentLoan.setLoanStatus(loanStatus);
        return currentLoan;
    }
}