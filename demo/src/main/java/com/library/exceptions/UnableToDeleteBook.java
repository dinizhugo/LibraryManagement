package com.library.exceptions;

public class UnableToDeleteBook extends Exception {
    public UnableToDeleteBook() {
        super("This book has been borrowed. To avoid losing the book, wait for it to be returned so that you can delete it from stock.");
    }
}
