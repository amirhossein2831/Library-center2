package com.Library.Controller;

import com.Library.Models.Category;
import com.Library.Models.Library;
import com.Library.Models.Users.Admin;
import com.Library.Models.Users.User;

import java.util.HashMap;

public abstract class BaseController {
    protected final HashMap<String, Library> libraries;
    protected final HashMap<String, Category> categories;
    protected HashMap<String, User> users;

    protected BaseController() {
        libraries = new HashMap<>();
        categories = new HashMap<>();
        users = new HashMap<>();
        Admin admin = new Admin("admin", "AdminPass", "AmirHossein", "Motaghian", "000000000", "19", "Tehran,AUT");
        users.put(admin.getId(), admin);
    }

    protected int countBorrow(String userId) {
        int x = 0;
        for (Library library : libraries.values()) {
            x += library.countBorrow(userId);
        }
        return x;
    }
}
