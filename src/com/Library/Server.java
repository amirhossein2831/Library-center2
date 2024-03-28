package com.Library;

import com.Library.Compunent.Exception.InvalidPassException;
import com.Library.Compunent.Exception.NotFoundException;
import com.Library.Compunent.Exception.PermissionDeniedException;
import com.Library.Compunent.Parser.Parser;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Server {
    private final Parser parser;
    private final Scanner scanner;

    public Server() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        this.parser = new Parser();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        String input = scanner.nextLine();
        while (!input.equals("finish")) {
            try {
                parser.parsCommand(input);
            } catch (NotFoundException |
                     InvalidPassException |
                     PermissionDeniedException |
                     ClassNotFoundException |
                     NoSuchMethodException |
                     InstantiationException |
                     IllegalAccessException e) {
                System.out.println(e.getMessage());
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof RuntimeException originalRuntimeException) {
                    System.out.println(originalRuntimeException.getMessage());
                }
            }
            input = scanner.nextLine();
        }
        scanner.close();
    }
}

