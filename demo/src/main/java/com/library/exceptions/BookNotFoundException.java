package com.library.exceptions;

public class BookNotFoundException extends Exception{
    public BookNotFoundException() {
        super("No books were found.");
    }
}
