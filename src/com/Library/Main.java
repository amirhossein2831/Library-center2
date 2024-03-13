package com.Library;

import com.Library.Compunent.Exception.InvalidPassException;
import com.Library.Compunent.Exception.NotFoundException;
import com.Library.Compunent.Exception.PermissionDeniedException;
import com.Library.Compunent.Parser.Parser;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        String input = scanner.nextLine();
        while (!input.equals("finish")) {
            try {
                parser.parsCommand(input);
            } catch (NotFoundException | InvalidPassException | PermissionDeniedException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                     InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            input = scanner.nextLine();
        }
    }
}