package com.Library.Compunent.Router;

import com.Library.Auth.Auth;
import com.Library.Compunent.Reflection.Reflection;
import com.Library.Controller.AdminController;
import com.Library.Controller.FeatureController;
import com.Library.Controller.ManagerController;
import com.Library.Controller.SearchController;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Route {
    private final Reflection adminReflect;
    private final Reflection managerReflect;
    private final Reflection featureReflect;
    private final Reflection searchReflect;
    private HashMap<String, String> route;

    public Route() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        adminReflect = new Reflection(AdminController.class);
        managerReflect = new Reflection(ManagerController.class);
        featureReflect = new Reflection(FeatureController.class);
        searchReflect = new Reflection(SearchController.class);
        fetchRoute();
    }

    public void routing(String path, String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        if (!route.containsKey(path)) {
            System.out.println("invalid route");
            return;
        }

        String className = route.get(path).split("@")[0];
        String method = route.get(path).split("@")[1];
        if (className.contains("Admin")) {
            Auth.adminAuth(args[0], args[1]);
            Object res = adminReflect.call(Class.forName(className), method, args);
            System.out.println((String) res);
        } else if (className.contains("Manager")) {
            Auth.managerAuth(args[0], args[1]);
            Object res = managerReflect.call(Class.forName(className), method, args);
            System.out.println((String) res);
        } else if (className.contains("Feature")) {
            Auth.featureAuth(args[0], args[1],args[2],args[3]);
            Object res = featureReflect.call(Class.forName(className), method, args);
            System.out.println((String) res);
        } else if (className.contains("Search")) {
            Object res = searchReflect.call(Class.forName(className), method, args);
            System.out.println((String) res);
        }
    }

    public void fetchRoute() {
        // Define all Route here
        route = new HashMap<>() {{
            put("add-library", "com.Library.Controller.AdminController@addLibrary");
            put("add-category", "com.Library.Controller.AdminController@addCategory");
            put("add-student", "com.Library.Controller.AdminController@addStudent");
            put("add-staff", "com.Library.Controller.AdminController@addStaff");
            put("add-manager", "com.Library.Controller.AdminController@addManager");
            put("remove-user", "com.Library.Controller.AdminController@removeUser");
            put("add-book", "com.Library.Controller.ManagerController@addBook");
            put("add-thesis", "com.Library.Controller.ManagerController@addThesis");
            put("add-ganjineh-book", "com.Library.Controller.ManagerController@addGanjine");
            put("add-seeling-book", "com.Library.Controller.ManagerController@addSellingBook");
            put("remove-resource", "com.Library.Controller.ManagerController@removeResource");
            put("borrow", "com.Library.Controller.FeatureController@borrow");
            put("return", "com.Library.Controller.FeatureController@returning");
            put("buy", "com.Library.Controller.FeatureController@buy");
            put("read", "com.Library.Controller.FeatureController@read");
            put("add-comment", "com.Library.Controller.FeatureController@addComment");
            put("search", "com.Library.Controller.SearchController@search");
            put("search-user", "com.Library.Controller.SearchController@searchUser");
            put("report-penalties-sum", "com.Library.Controller.AdminController@reportPenalties");
            put("report-passed-deadline", "com.Library.Controller.ManagerController@reportPassedDeadLine");
            put("library-report", "com.Library.Controller.ManagerController@libraryReport");
            put("category-report", "com.Library.Controller.ManagerController@categoryReport");
            put("report-most-popular", "com.Library.Controller.ManagerController@reportMostPopular");
            put("report-sell", "com.Library.Controller.ManagerController@reportSelling");
        }};
    }
}
