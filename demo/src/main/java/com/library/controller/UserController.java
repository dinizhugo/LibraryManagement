package com.library.controller;

import com.library.exceptions.UninformedParameterException;
import com.library.exceptions.UserNotFoundException;
import com.library.model.entities.User;
import com.library.model.service.UserService;

import java.util.List;

public class UserController {
    private final UserService userService = new UserService();

    public void createNewUser(String name, String email, String phone, String address) throws UninformedParameterException {
        if (name == null && email == null && phone == null && address == null) {
            throw new UninformedParameterException();
        }
        userService.createNewUser(new User(null, name, email, phone, address));
    }

    public void updateUser(User user, String name, String email, String phone, String address) {
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        userService.updateUser(user);
    }

    public void deleteUser(Integer id) {
        userService.deleteUserById(id);
    }

    public User getUser(Integer id) throws UserNotFoundException {
        User user = userService.getUserById(id);

        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
