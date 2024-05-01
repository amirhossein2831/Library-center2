package com.Library.Component.Exception;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException() {
        super("permission-denied");
    }
}
