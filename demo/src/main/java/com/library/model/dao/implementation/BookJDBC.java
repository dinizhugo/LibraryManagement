package com.library.model.dao.implementation;

import com.library.database.DB;
import com.library.database.DbException;
import com.library.model.dao.GenericDao;
import com.library.model.entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookJDBC implements GenericDao<Book> {

    private final Connection connection;

    public BookJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Book obj) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO books "
                    +   "(titulo, autor, categoria, data_publicacao, editora, quantidade)"
                    +   "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, obj.getTitle());
            preparedStatement.setString(2, obj.getAuthor());
            preparedStatement.setString(3, obj.getCategory());
            preparedStatement.setDate(4, java.sql.Date.valueOf(obj.getPublicationDate()));
            preparedStatement.setString(5, obj.getPublishingCompany());
            preparedStatement.setInt(6, obj.getAmount());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    obj.setId(id);
                }
                DB.closeResultSet(resultSet);
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
    public void update(Book obj) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE books "
                    + "SET (titulo = ?, autor = ?, categoria = ?, data_publicacao = ?, editora = ?, quantidade = ?) "
                    + "WHERE id = ?");
            preparedStatement.setString(1, obj.getTitle());
            preparedStatement.setString(2, obj.getAuthor());
            preparedStatement.setString(3, obj.getCategory());
            preparedStatement.setDate(4, java.sql.Date.valueOf(obj.getPublicationDate()));
            preparedStatement.setString(5, obj.getPublishingCompany());
            preparedStatement.setInt(6, obj.getAmount());
            preparedStatement.setInt(7, obj.getId());


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
                    "DELETE FROM books " +
                            "WHERE id = ?"
            );

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public Book findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM books " +
                            "WHERE Id = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return instantiateBook(resultSet);
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
    public List<Book> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM books");
            resultSet = preparedStatement.executeQuery();

            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = instantiateBook(resultSet);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    private Book instantiateBook(ResultSet resultSet) throws SQLException {
        Book currentBook = new Book();
        currentBook.setId(resultSet.getInt("id"));
        currentBook.setTitle(resultSet.getString("titulo"));
        currentBook.setAuthor(resultSet.getString("autor"));
        currentBook.setPublishingCompany(resultSet.getString("editora"));
        currentBook.setAmount(resultSet.getInt("quantidade"));
        currentBook.setCategory(resultSet.getString("categoria"));
        currentBook.setPublicationDate(resultSet.getDate("data_publicacao").toLocalDate());
        return currentBook;
    }
}
