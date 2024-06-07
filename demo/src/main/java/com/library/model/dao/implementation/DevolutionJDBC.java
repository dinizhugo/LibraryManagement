package com.library.model.dao.implementation;

import com.library.database.DB;
import com.library.database.DbException;
import com.library.model.dao.DevolutionDao;
import com.library.model.entities.Book;
import com.library.model.entities.Devolution;
import com.library.model.entities.Loan;
import com.library.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevolutionJDBC implements DevolutionDao {

    private final Connection connection;

    public DevolutionJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Devolution obj) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO devolution " +
                            "(id_loan, return_date, traffic_ticket) " +
                            "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, obj.getLoan().getId());
            preparedStatement.setDate(2, java.sql.Date.valueOf(obj.getReturnDate()));
            preparedStatement.setDouble(3, obj.getTrafficTicket());

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
    public void update(Devolution obj) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE devolution " +
                            "SET id_loan = ?, return_date = ?, traffic_ticket = ? " +
                            "WHERE id = ?");
            preparedStatement.setInt(1, obj.getLoan().getId());
            preparedStatement.setDate(2, java.sql.Date.valueOf(obj.getReturnDate()));
            preparedStatement.setDouble(3, obj.getTrafficTicket());
            preparedStatement.setInt(4, obj.getId());

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
                    "DELETE FROM devolution " +
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
    public Devolution findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT devolution.*, loans.*, users.name AS user_name, users.email AS user_email, users.phone AS user_phone, users.address AS user_address, users.id AS id_user, " +
                            "books.titulo AS book_title, books.autor AS book_author, books.editora AS book_publishingCompany, books.quantidade AS book_amount, books.categoria AS book_category, books.data_publicacao AS book_publicationDate, books.id AS id_book " +
                            "FROM devolution " +
                            "INNER JOIN loans ON devolution.id_loan = loans.id " +
                            "INNER JOIN users ON loans.id_user = users.id " +
                            "INNER JOIN books ON loans.id_book = books.id " +
                            "WHERE devolution.id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Loan loan = instantiateLoan(resultSet);
                return instantiateDevolution(resultSet, loan);
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
    public Devolution findByLoan(Loan loan) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT devolution.*, loans.*, users.name AS user_name, users.email AS user_email, users.phone AS user_phone, users.address AS user_address, users.id AS id_user, " +
                            "books.titulo AS book_title, books.autor AS book_author, books.editora AS book_publishingCompany, books.quantidade AS book_amount, books.categoria AS book_category, books.data_publicacao AS book_publicationDate, books.id AS id_book " +
                            "FROM devolution " +
                            "INNER JOIN loans ON devolution.id_loan = loans.id " +
                            "INNER JOIN users ON loans.id_user = users.id " +
                            "INNER JOIN books ON loans.id_book = books.id " +
                            "WHERE devolution.id_loan = ?");
            preparedStatement.setInt(1, loan.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return instantiateDevolution(resultSet, loan);
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
    public List<Devolution> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT devolution.*, loans.*, users.name AS user_name, users.email AS user_email, users.phone AS user_phone, users.address AS user_address, users.id AS id_user, " +
                            "books.titulo AS book_title, books.autor AS book_author, books.editora AS book_publishingCompany, books.quantidade AS book_amount, books.categoria AS book_category, books.data_publicacao AS book_publicationDate, books.id AS id_book " +
                            "FROM devolution " +
                            "INNER JOIN loans ON devolution.id_loan = loans.id " +
                            "INNER JOIN users ON loans.id_user = users.id " +
                            "INNER JOIN books ON loans.id_book = books.id " +
                            "ORDER BY devolution.id");
            resultSet = preparedStatement.executeQuery();

            List<Devolution> devolutions = new ArrayList<>();

            while (resultSet.next()) {
                Loan loan = instantiateLoan(resultSet);
                Devolution devolution = instantiateDevolution(resultSet, loan);
                devolutions.add(devolution);
            }
            return devolutions;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    private Loan instantiateLoan(ResultSet resultSet) throws SQLException {
        User user = instantiateUser(resultSet);
        Book book = instantiateBook(resultSet);
        Loan loan = new Loan();
        loan.setId(resultSet.getInt("id_loan"));
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(resultSet.getDate("loan_date").toLocalDate());
        loan.setEstimatedDate(resultSet.getDate("estimated_date").toLocalDate());
        return loan;
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

    private Devolution instantiateDevolution(ResultSet resultSet, Loan loan) throws SQLException {
        Devolution devolution = new Devolution();
        devolution.setId(resultSet.getInt("id"));
        devolution.setLoan(loan);
        devolution.setReturnDate(resultSet.getDate("return_date").toLocalDate());
        devolution.setTrafficTicket(resultSet.getDouble("traffic_ticket"));
        return devolution;
    }


}
