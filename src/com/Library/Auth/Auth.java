package com.Library.Auth;

import com.Library.Compunent.Exception.InvalidPassException;
import com.Library.Compunent.Exception.NotFoundException;
import com.Library.Compunent.Exception.PermissionDeniedException;
import com.Library.Models.LibraryCenter;
import com.Library.Models.Users.Admin;
import com.Library.Models.Users.Manager;

public class Auth {
    public static void adminAuth(String id, String pass) {
        if (LibraryCenter.getUsers().get(id) == null) {
            throw new NotFoundException();
        } else if (!(LibraryCenter.getUsers().get(id) instanceof Admin)) {
            throw new PermissionDeniedException();
        }
        if (!LibraryCenter.getUsers().get(id).getPass().equals(pass)) {
            throw new InvalidPassException();
        }
    }

    public static void managerAuth(String id, String pass) {
        if (LibraryCenter.getUsers().get(id) == null) {
            throw new NotFoundException();
        } else if (!LibraryCenter.getUsers().get(id).getPass().equals(pass)) {
            throw new InvalidPassException();
        }
        if (!(LibraryCenter.getUsers().get(id) instanceof Manager)) {
            throw new PermissionDeniedException();
        }
    }

    public static void libraryManagerAuth(String id,String libraryId) {
        if (LibraryCenter.getLibraries().get(libraryId) == null) {
            throw new NotFoundException();
        }
        if (!((Manager) LibraryCenter.getUsers().get(id)).getLibraryId().equals(libraryId)) {
            throw new PermissionDeniedException();
        }
    }
}
