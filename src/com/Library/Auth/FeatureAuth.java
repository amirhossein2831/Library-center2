package com.Library.Auth;

import com.Library.Compunent.Exception.InvalidPassException;
import com.Library.Compunent.Exception.NotFoundException;
import com.Library.Models.LibraryCenter;

import java.util.HashMap;

public class FeatureAuth extends Auth{
    @Override
    public void authenticate(HashMap<String, String> args) {
        String id = args.get("id");
        String pass = args.get("pass");
        String libraryId = args.get("libraryId");
        String resourceId = args.get("resourceId");
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
