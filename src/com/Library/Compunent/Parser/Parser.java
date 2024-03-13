package com.Library.Compunent.Parser;

import com.Library.Compunent.Router.Route;

import java.lang.reflect.InvocationTargetException;

public class Parser {

    private final Route router;

    public Parser() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        router = new Route();
    }

    public void parsCommand(String input) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String[] command = input.split("#");
        if (command.length < 2) {
            System.out.println("invalid input");
            return;
        }
        String[] args = command[1].split("\\|");
        router.routing(command[0], args);
    }
}

