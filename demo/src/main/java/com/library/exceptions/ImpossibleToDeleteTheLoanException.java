package com.library.exceptions;

public class ImpossibleToDeleteTheLoanException extends Exception {
    public ImpossibleToDeleteTheLoanException() {
        super("It's not possible to delete a loan that has not yet been returned.");
    }
}
