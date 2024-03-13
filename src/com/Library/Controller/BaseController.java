package com.Library.Controller;

import com.Library.Models.Category;
import com.Library.Models.Library;
import com.Library.Models.LibraryCenter;
import com.Library.Models.Users.Admin;
import com.Library.Models.Users.User;

import java.util.HashMap;

public abstract class BaseController {

    protected BaseController() {
        Admin admin = new Admin("admin", "AdminPass", "AmirHossein", "Motaghian", "000000000", "19", "Tehran,AUT");
        LibraryCenter.getUsers().put(admin.getId(), admin);
    }

    protected int countBorrow(String userId) {
        int x = 0;
        for (Library library : LibraryCenter.getLibraries().values()) {
            x += library.countBorrow(userId);
        }
        return x;
    }
}
