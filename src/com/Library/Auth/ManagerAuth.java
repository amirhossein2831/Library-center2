package com.Library.Auth;

import com.Library.Compunent.Exception.InvalidPassException;
import com.Library.Compunent.Exception.NotFoundException;
import com.Library.Compunent.Exception.PermissionDeniedException;
import com.Library.Models.LibraryCenter;
import com.Library.Models.Users.Manager;

public class ManagerAuth extends Auth {
    @Override
    public void authenticate(String... args) {
        String id = args[0];
        String pass = args[1];
        if (LibraryCenter.getUsers().get(id) == null) {
            throw new NotFoundException();
        }
        if (!LibraryCenter.getUsers().get(id).getPass().equals(pass)) {
            throw new InvalidPassException();
        }
    }
    public static void libraryManagerAuth(String id, String libraryId) {
        if (LibraryCenter.getLibraries().get(libraryId) == null) {
            throw new NotFoundException();
        }
        if (!(LibraryCenter.getUsers().get(id) instanceof Manager)) {
            throw new PermissionDeniedException();
        }
        if (!((Manager) LibraryCenter.getUsers().get(id)).getLibraryId().equals(libraryId) ) {
            throw new PermissionDeniedException();
        }
    }
}

