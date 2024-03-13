package com.Library;

import com.Library.Compunent.Exception.InvalidPassException;
import com.Library.Compunent.Exception.NotFoundException;
import com.Library.Compunent.Exception.PermissionDeniedException;
import com.Library.Compunent.Parser.Parser;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        String input = scanner.nextLine();
        while (!input.equals("finish")) {
            try {
                parser.parsCommand(input);
            } catch (NotFoundException | InvalidPassException | PermissionDeniedException e) {
                System.out.println(e.getMessage());
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof RuntimeException) {
                        RuntimeException originalRuntimeException = (RuntimeException) cause;
                        System.out.println(originalRuntimeException.getMessage());
                }
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            input = scanner.nextLine();
        }
    }
}