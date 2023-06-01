package com.Library.Compunent.Rule;

import com.Library.Compunent.Exception.InvalidPassException;
import com.Library.Compunent.Exception.NotFoundException;
import com.Library.Compunent.Exception.PermissionDeniedException;
import com.Library.Models.Library;
import com.Library.Models.Users.Admin;
import com.Library.Models.Users.Manager;
import com.Library.Models.Resources.Resource;
import com.Library.Models.Users.User;

import java.util.HashMap;

public class Rule {
    private User user;
    private HashMap<String, User> users;
    private Library library;
    private HashMap<String, Library> libraries;
    private Resource resource;
    public Rule(String userId, String pass, HashMap<String, User> users) {
        setUsers(users);
        isAdmin(userId, pass);
        this.user = users.get(userId);
    }
    public Rule(String userId,String pass,String libraryId,HashMap<String, User> users,HashMap<String, Library> libraries) {
        setUsers(users);
        setLibraries(libraries);
        isManager(userId, pass, libraryId);
        this.user = users.get(userId);
        this.library = libraries.get(libraryId);

    }
    public Rule(String userId,String pass,String libraryId,String resourceId,HashMap<String, User> users,HashMap<String, Library> libraries){
        setUsers(users);
        setLibraries(libraries);
        checkLibrary(userId, pass, libraryId, resourceId);
        this.user = users.get(userId);
        this.library = libraries.get(libraryId);
        this.resource = library.getResource(resourceId);
    }
    private void isAdmin(String userId, String adminPass) {
        if (users.get(userId) == null) {
            throw new NotFoundException();
        } else if (!(users.get(userId) instanceof Admin)) {
            throw new PermissionDeniedException();
        }
        if (!users.get(userId).getPass().equals(adminPass)) {
            throw new InvalidPassException();
        }
    }
    private void isManager(String userId, String pass, String libraryId) {
        if (users.get(userId) == null) {
            throw new NotFoundException();
        } else if (!users.get(userId).getPass().equals(pass)) {
            throw new InvalidPassException();
        }
        if (libraries.get(libraryId) == null) {
            throw new NotFoundException();
        }
        if (!(users.get(userId) instanceof Manager)) {
            throw new PermissionDeniedException();
        }
        if (!((Manager) users.get(userId)).getLibraryId().equals(libraryId)) {
            throw new PermissionDeniedException();
        }
    }
    private void checkLibrary(String userId, String pass, String libraryId, String resourceId) {
        if (users.get(userId) == null) {
            throw new NotFoundException();
        } else if (!users.get(userId).getPass().equals(pass)) {
            throw new InvalidPassException();
        }
        if (libraries.get(libraryId) == null) {
            throw new NotFoundException();
        }
        if (libraries.get(libraryId).getResource(resourceId) == null) {
            throw new NotFoundException();
        }
    }
    public User getUser() {
        return user;
    }
    public Library getLibrary() {
        return library;
    }
    public Resource getResource() {
        return resource;
    }
    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }
    public void setLibraries(HashMap<String, Library> libraries) {
        this.libraries = libraries;
    }
}
