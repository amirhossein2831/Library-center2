package com.Library.Auth;

import com.Library.Compunent.Exception.NotFoundException;
import com.Library.Compunent.Exception.PermissionDeniedException;
import com.Library.Models.LibraryCenter;
import com.Library.Models.Users.Manager;

import java.util.HashMap;

public class LibraryManagerAuth extends Auth{
    @Override
    public void authenticate(HashMap<String, String> args) {
        String id = args.get("id");
        String libraryId = args.get("libraryId");
        if (LibraryCenter.getLibraries().get(libraryId) == null) {
            throw new NotFoundException();
        }
        if (!((Manager) LibraryCenter.getUsers().get(id)).getLibraryId().equals(libraryId)) {
            throw new PermissionDeniedException();
        }
    }
}
