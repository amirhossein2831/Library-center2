package com.Library.Compunent.Router;

import com.Library.Auth.AdminAuth;
import com.Library.Auth.Auth;
import com.Library.Auth.FeatureAuth;
import com.Library.Auth.ManagerAuth;
import com.Library.Compunent.Reflection.Reflection;
import com.Library.Controller.AdminController;
import com.Library.Controller.FeatureController;
import com.Library.Controller.ManagerController;
import com.Library.Controller.SearchController;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class Route {
    private final ArrayList<SubRoute> routes;

    public Route() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        routes = new ArrayList<>();
        fetchRoute();
    }

    public void registerRoute(Class<?> controllerClass, Auth auth, HashMap<String, String> routes) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        SubRoute subRoute = new SubRoute(new Reflection(controllerClass), routes,auth);
        this.routes.add(subRoute);
    }

    public void routing(String path, String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        for (SubRoute sb : routes) {
            if (sb.getRoutes().containsKey(path)){
                String methodName = sb.getRoutes().get(path);
                if (sb.getAuth() != null) {
                    sb.getAuth().authenticate(args);
                }
                Object res = sb.getReflect().call(methodName, args);
                System.out.println((String) res);
            }
        }
    }

    private void fetchRoute() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        registerRoute(AdminController.class, new AdminAuth(), new HashMap<>() {{
            put("add-library", "addLibrary");
            put("add-category", "addCategory");
            put("add-student", "addStudent");
            put("add-staff", "addStaff");
            put("add-manager", "addManager");
            put("remove-user", "removeUser");
            put("report-penalties-sum", "reportPenalties");
        }});
        registerRoute(ManagerController.class,new ManagerAuth(), new HashMap<>() {{
            put("add-book", "addBook");
            put("add-thesis", "addThesis");
            put("add-ganjineh-book", "addGanjine");
            put("add-selling-book", "addSellingBook");
            put("remove-resource", "removeResource");
            put("report-passed-deadline", "reportPassedDeadLine");
            put("library-report", "libraryReport");
            put("category-report", "categoryReport");
            put("report-most-popular", "reportMostPopular");
            put("report-sell", "reportSelling");
        }});
        registerRoute(FeatureController.class,new FeatureAuth(), new HashMap<>() {{
            put("borrow", "borrow");
            put("return", "returning");
            put("buy", "buy");
            put("read", "read");
            put("add-comment", "addComment");
        }});
        registerRoute(SearchController.class, null, new HashMap<>() {{
            put("search", "search");
            put("search-user", "searchUser");
        }});
    }
}
