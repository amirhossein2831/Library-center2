package com.Library.Compunent.Exception;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException() {
        super("permission-denied");
    }
}
