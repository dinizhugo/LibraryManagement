package com.library.controller;

import com.library.exceptions.UnableToDeleteUser;
import com.library.exceptions.UninformedParameterException;
import com.library.exceptions.UserAlreadyExistsException;
import com.library.exceptions.UserNotFoundException;
import com.library.model.entities.Loan;
import com.library.model.entities.LoanStatus;
import com.library.model.entities.User;
import com.library.model.service.LoanService;
import com.library.model.service.UserService;

import java.util.List;
import java.util.Objects;

public class UserController {
    private final UserService userService = new UserService();

    public void createNewUser(String name, String email, String phone, String address) throws UninformedParameterException, UserAlreadyExistsException {
        if (name == null && email == null && phone == null && address == null) {
            throw new UninformedParameterException();
        }

        if (isThereAUser(email)) {
            throw new UserAlreadyExistsException();
        }
        userService.createNewUser(new User(null, name, email, phone, address));
    }

    public void updateUser(User user, String name, String email, String phone, String address) throws UninformedParameterException, UserAlreadyExistsException {
        if (name == null && email == null && phone == null && address == null) {
            throw new UninformedParameterException();
        }

        if (isThereAUser(email)) {
            throw new UserAlreadyExistsException();
        }
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        userService.updateUser(user);
    }

    public void deleteUser(Integer id) throws UserNotFoundException, UnableToDeleteUser {
        if (thisUserHasALoan(id)) {
            throw new UnableToDeleteUser();
        }
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

    public boolean isThereAUser(String email) {
        return getUsers().stream().anyMatch(user -> Objects.equals(user.getEmail(), email));
    }

    private boolean thisUserHasALoan(Integer id) throws UserNotFoundException {
        User user = getUser(id);

        if (user != null) {
            LoanService loanService = new LoanService();
            List<Loan> loans = loanService.getLoanByUser(user).stream().filter(loan -> loan.getLoanStatus() == LoanStatus.NOT_RETURNED).toList();

            return !loans.isEmpty();
        }
        return false;
    }
}
