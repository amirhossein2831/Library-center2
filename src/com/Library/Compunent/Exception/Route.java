package com.Library.Compunent.Exception;

import java.util.HashMap;

public class Route {
    private HashMap<String,String> route;

    public Route() {
        this.route = new HashMap<>();
    }

    public void addRoutes(HashMap<String,String> route) {
        this.route = route;
    }

    public HashMap<String, String> getRoute() {
        return route;
    }
}
