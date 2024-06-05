package com.library.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("No users were found.");
    }
}
