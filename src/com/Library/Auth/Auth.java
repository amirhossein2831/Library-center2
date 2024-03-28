package com.Library.Auth;

import com.Library.Compunent.Exception.NotFoundException;
import com.Library.Compunent.Exception.PermissionDeniedException;
import com.Library.Models.LibraryCenter;
import com.Library.Models.Users.Manager;

import java.util.HashMap;

public abstract class Auth {
    public abstract void authenticate(String ...args);
}
