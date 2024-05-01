package com.Library.Component.Exception;

public class InvalidPassException extends RuntimeException {
    public InvalidPassException() {
        super("invalid-pass");
    }
}
