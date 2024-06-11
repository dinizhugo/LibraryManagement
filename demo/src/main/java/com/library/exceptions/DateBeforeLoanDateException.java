package com.library.exceptions;

public class DateBeforeLoanDateException extends Exception {
    public DateBeforeLoanDateException() {
        super("It is not possible to change the date before the loan date.");
    }
}
