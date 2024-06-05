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

    public void createNewUser(String name, String email, String phone, String address) {
        userDao.insert(new User(null, name, email, phone, address));
    }

    public void updateUser(User user, String name, String email, String phone, String address) {
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        userDao.update(user);
    }

    public void deleteStudentById(Integer id) {
        userDao.deleteById(id);
    }

    public User getStudentById(Integer id) {
        return userDao.findById(id);
    }

    public List<User> getAllStudents() {
        return userDao.findAll();
    }

}
