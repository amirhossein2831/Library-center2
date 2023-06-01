package com.Library.Compunent.Exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("not-found");
    }
}
