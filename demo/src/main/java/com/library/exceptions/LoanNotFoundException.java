package com.library.exceptions;

public class LoanNotFoundException extends Exception {
    public LoanNotFoundException() {
        super("No loans were found.");
    }
}
