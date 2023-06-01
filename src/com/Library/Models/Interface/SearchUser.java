package com.Library.Models.Interface;

import com.Library.Models.Users.User;

import java.util.ArrayList;
import java.util.HashSet;

public interface SearchUser {
    public HashSet<String> searchUser(ArrayList<User> users, String key);
}
