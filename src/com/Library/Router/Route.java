package com.Library.Router;

import com.Library.Auth.Auth;
import com.Library.Compunent.Reflection.Reflection;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class Route {
    private static Route instance;
    private final ArrayList<SubRoute> routes;

    private Route() {
        routes = new ArrayList<>();
    }

    public static Route getInstance() {
        if (instance == null) {
            instance = new Route();
        }
        return instance;
    }

    public void registerRoute(Class<?> controllerClass, Auth auth, HashMap<String, String> routes) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        SubRoute subRoute = new SubRoute(new Reflection(controllerClass), routes, auth);
        this.routes.add(subRoute);
    }

    public void routing(String path, String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        for (SubRoute sb : routes) {
            if (sb.getRoutes().containsKey(path)) {
                String methodName = sb.getRoutes().get(path);
                if (sb.getAuth() != null) {
                    sb.getAuth().authenticate(args);
                }
                Object res = sb.getReflect().call(methodName, args);
                System.out.println((String) res);
            }
        }
    }

    public void extractRoute() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ClassLoader classLoader = getClass().getClassLoader();
            File jsonFile = new File(Objects.requireNonNull(classLoader.getResource("com/Library/Router/routes.json")).getFile());
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            if (rootNode.isArray()) {
                Iterator<JsonNode> routesIterator = rootNode.elements();
                while (routesIterator.hasNext()) {
                    JsonNode routeNode = routesIterator.next();
                    String controller = routeNode.get("Controller").asText();
                    String auth = routeNode.get("Auth").asText();
                    JsonNode methodNode = routeNode.get("Method");

                    HashMap<String, String> methods = new HashMap<>();
                    methodNode.fields().forEachRemaining(entry -> {
                        methods.put(entry.getKey(), entry.getValue().asText());
                    });

                    generateRegistrationCode(controller, auth, methods);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void generateRegistrationCode(String controller, String auth, HashMap<String, String> methods) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class<?> controllerClass = Class.forName(controller);
        if (auth.isEmpty()) {
            this.registerRoute(controllerClass, null, methods);
            return;
        }
        Class<?> authClass = Class.forName(auth);
        Object authInstance = authClass.getDeclaredConstructor().newInstance();
        this.registerRoute(controllerClass, (Auth) authInstance, methods);
    }

}
