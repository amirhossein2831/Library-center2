package com.Library.Models;

import com.Library.Models.Users.User;

import java.util.HashMap;

public class LibraryCenter {
    private final static HashMap<String, Library> libraries = new HashMap<>();
    private final static HashMap<String, Category> categories = new HashMap<>();

    private final static HashMap<String, User> users = new HashMap<>();

    public static HashMap<String, Library> getLibraries() {
        return libraries;
    }

    public static HashMap<String, Category> getCategories() {
        return categories;
    }

    public static HashMap<String, User> getUsers() {
        return users;
    }
}
