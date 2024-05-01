package com.Library.Auth;

import com.Library.Component.Exception.InvalidPassException;
import com.Library.Component.Exception.NotFoundException;
import com.Library.Component.Exception.PermissionDeniedException;
import com.Library.Models.LibraryCenter;
import com.Library.Models.Users.Admin;

public class AdminAuth extends Auth{
    @Override
    public void authenticate(String ... args) {
        String id = args[0];
        String pass = args[1];
        if (LibraryCenter.getUsers().get(args[0]) == null) {
            throw new NotFoundException();
        } else if (!(LibraryCenter.getUsers().get(id) instanceof Admin)) {
            throw new PermissionDeniedException();
        }
        if (!LibraryCenter.getUsers().get(id).getPass().equals(pass)) {
            throw new InvalidPassException();
        }
    }
}