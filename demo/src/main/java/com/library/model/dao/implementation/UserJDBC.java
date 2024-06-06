package com.library.model.dao.implementation;

import com.library.database.DB;
import com.library.database.DbException;
import com.library.model.dao.GenericDao;
import com.library.model.entities.Book;
import com.library.model.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBC implements GenericDao<User> {

    private final Connection connection;

    public UserJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(User obj) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users "
                            +   "(name, email, phone, address)"
                            +   "VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getEmail());
            preparedStatement.setString(3, obj.getPhone());
            preparedStatement.setString(4, obj.getAddress());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
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
    public void update(User obj) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE users "
                            + "SET name = ?, email = ?, phone = ?, address = ? "
                            + "WHERE id = ?");
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getEmail());
            preparedStatement.setString(3, obj.getPhone());
            preparedStatement.setString(4, obj.getAddress());
            preparedStatement.setInt(5, obj.getId());

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
                    "DELETE FROM users " +
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
    public User findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users " +
                            "WHERE Id = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return instantiateUser(resultSet);
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
    public List<User> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM users");
            resultSet = preparedStatement.executeQuery();

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = instantiateUser(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    private User instantiateUser(ResultSet resultSet) throws SQLException {
        User currentUser = new User();
        currentUser.setId(resultSet.getInt("id"));
        currentUser.setName(resultSet.getString("name"));
        currentUser.setEmail(resultSet.getString("email"));
        currentUser.setPhone(resultSet.getString("phone"));
        currentUser.setAddress(resultSet.getString("address"));
        return currentUser;
    }
}
