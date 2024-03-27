package com.Library.Auth;

import com.Library.Compunent.Exception.InvalidPassException;
import com.Library.Compunent.Exception.NotFoundException;
import com.Library.Compunent.Exception.PermissionDeniedException;
import com.Library.Models.LibraryCenter;
import com.Library.Models.Users.Admin;
import java.util.HashMap;

public class AdminAuth extends Auth{
    @Override
    public void authenticate(HashMap<String, String> args) {
        String id = args.get("id");
        String pass = args.get("pass");
        if (LibraryCenter.getUsers().get(args.get("id")) == null) {
            throw new NotFoundException();
        } else if (!(LibraryCenter.getUsers().get(id) instanceof Admin)) {
            throw new PermissionDeniedException();
        }
        if (!LibraryCenter.getUsers().get(id).getPass().equals(pass)) {
            throw new InvalidPassException();
        }
    }
}
