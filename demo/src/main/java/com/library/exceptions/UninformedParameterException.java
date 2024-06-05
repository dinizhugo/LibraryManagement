package com.library.exceptions;

public class UninformedParameterException  extends Exception {
    public UninformedParameterException() {
        super("Parameters were not entered correctly");
    }
}
