package com.Library.Auth;

import com.Library.Compunent.Exception.InvalidPassException;
import com.Library.Compunent.Exception.NotFoundException;
import com.Library.Compunent.Exception.PermissionDeniedException;
import com.Library.Models.LibraryCenter;
import com.Library.Models.Users.Admin;

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
}
