package com.Library.Controller;

import com.Library.Models.Interface.SearchUser;
import com.Library.Models.Library;
import com.Library.Models.LibraryCenter;
import com.Library.Models.Resources.Resource;
import com.Library.Models.Users.Student;
import com.Library.Models.Users.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class SearchController extends BaseController {

    public String search(String[] args) {
        String key = args[0];
        StringBuilder str = new StringBuilder();
        HashSet<StringBuilder> values = new HashSet<>();
        for (Library library : LibraryCenter.getLibraries().values()) {
            for (Resource resource : library.getResources().values()) {
                StringBuilder s = resource.search(key);
                if (s.length() != 0) {
                    values.add(resource.search(key));
                }
            }
        }
        ArrayList<StringBuilder> hold = new ArrayList<>(values);
        Collections.sort(hold);
        for (StringBuilder temp : hold) {
            str.append(temp);
            str.append("|");
        }
        if (str.length() != 0) {
            str.deleteCharAt(str.length() - 1);
            return new String(str);
        }
        return "not-found";
    }

    public String searchUser(String[] args) {
        String userId = args[0];
        String pass = args[1];
        String key = args[2];
        User user = LibraryCenter.getUsers().get(userId);
        StringBuilder str = new StringBuilder();
        HashSet<String> values;
        if (user == null) {
            return "not-found";
        } else if (!user.getPass().equals(pass)) {
            return "invalid-pass";
        }
        if (user instanceof Student) {
            return "permission-denied";
        }
        Collection<User> collection = LibraryCenter.getUsers().values();
        ArrayList<User> usersHold = new ArrayList<>(collection);
        values = ((SearchUser) user).searchUser(usersHold, key);
        if (values == null) {
            return "not-found";
        }
        ArrayList<String> hold = new ArrayList<>(values);
        Collections.sort(hold);
        for (String temp : hold) {
            str.append(temp);
            str.append("|");
        }
        if (!str.isEmpty()) {
            str.deleteCharAt(str.length() - 1);
        }
        return new String(str);
    }
}
