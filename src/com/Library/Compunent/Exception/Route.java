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
            put("add-category", "addCategory");
            put("add-student", "addStudent");
            put("add-staff", "addStaff");
            put("add-manager", "addManager");
            put("remove-user", "removeUser");
            put("add-book", "addBook");
            put("add-thesis", "addThesis");
            put("add-ganjineh-book", "addGanjine");
            put("add-selling-book", "addSellingBook");
            put("remove-resource", "removeResource");
            put("borrow", "borrow");
            put("return", "returning");
            put("buy", "buy");
            put("read", "read");
            put("add-comment", "addComment");
            put("search", "search");
            put("search-user", "searchUser");
            put("report-penalties-sum", "reportPenalties");
            put("report-passed-deadline", "reportPassedDeadLine");
            put("library-report", "libraryReport");
            put("category-report", "categoryReport");
            put("report-most-popular", "reportMostPopular");
            put("report-sell", "reportSelling");
        }};
    }

    public HashMap<String, String> getRoute() {
        return route;
    }
}
