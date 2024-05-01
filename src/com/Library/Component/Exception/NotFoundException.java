package com.Library.Component.Exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("not-found");
    }
}
