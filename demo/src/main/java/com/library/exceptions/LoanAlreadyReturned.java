package com.library.exceptions;

public class LoanAlreadyReturned extends Exception {
    public LoanAlreadyReturned() {
        super("The loan has already been returned.");
    }
}
