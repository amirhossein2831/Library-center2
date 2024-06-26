package com.Library.Auth;

import com.Library.Component.Exception.InvalidPassException;
import com.Library.Component.Exception.NotFoundException;
import com.Library.Models.LibraryCenter;

public class FeatureAuth extends Auth{
    @Override
    public void authenticate(String ...args) {
        String id = args[0];
        String pass = args[1];
        String libraryId = args[2];
        String resourceId = args[3];
        if (LibraryCenter.getUsers().get(id) == null) {
            throw new NotFoundException();
        } else if (!LibraryCenter.getUsers().get(id).getPass().equals(pass)) {
            throw new InvalidPassException();
        }
        if (LibraryCenter.getLibraries().get(libraryId) == null) {
            throw new NotFoundException();
        }
        if (LibraryCenter.getLibraries().get(libraryId).getResource(resourceId) == null) {
            throw new NotFoundException();
        }
    }
}
