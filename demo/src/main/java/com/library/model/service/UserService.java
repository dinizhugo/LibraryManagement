package com.library.model.service;

import com.library.model.dao.DaoFactory;
import com.library.model.dao.GenericDao;
import com.library.model.entities.User;

import java.util.List;

public class UserService {
    private final GenericDao<User> userDao;

    public UserService() {
        this.userDao = DaoFactory.createUserDao();
    }

    public void createNewUser(User user) {
        userDao.insert(user);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    public void deleteUserById(Integer id) {
        userDao.deleteById(id);
    }

    public User getUserById(Integer id) {
        return userDao.findById(id);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

}
