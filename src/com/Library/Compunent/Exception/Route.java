package com.Library.Compunent.Exception;

import java.util.HashMap;

public class Route {
    private HashMap<String, String> route;

    public Route() {
        fetchRoute();
    }

    public void fetchRoute() {
        route = new HashMap<>() {{
            put("add-library", "addLibrary");
        }};
    }

    public HashMap<String, String> getRoute() {
        return route;
    }
}
