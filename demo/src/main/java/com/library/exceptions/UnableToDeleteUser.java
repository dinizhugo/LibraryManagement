package com.library.exceptions;

public class UnableToDeleteUser extends Exception {
    public UnableToDeleteUser() {
        super("This user has a loan. Wait for him to return the borrowed book before removing the user from the system.");
    }
}
