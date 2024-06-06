package com.library.exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException() {
        super("A User already exists with this email.");
    }
}
