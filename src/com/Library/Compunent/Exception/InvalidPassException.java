package com.Library.Compunent.Exception;

public class InvalidPassException extends RuntimeException {
    public InvalidPassException() {
        super("invalid-pass");
    }
}
