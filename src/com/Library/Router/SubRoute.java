package com.Library.Router;

import com.Library.Auth.Auth;
import com.Library.Component.Reflection.Reflection;

import java.util.HashMap;

public class SubRoute {
    private final Reflection reflect;
    private final HashMap<String, String> routes;
    private final Auth auth;

    public SubRoute(Reflection reflect, HashMap<String, String> route, Auth auth) {
        this.reflect = reflect;
        this.routes = route;
        this.auth = auth;
    }

    public Reflection getReflect() {
        return reflect;
    }

    public HashMap<String, String> getRoutes() {
        return routes;
    }

    public Auth getAuth() {
        return auth;
    }
}
