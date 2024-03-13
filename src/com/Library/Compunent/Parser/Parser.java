package com.Library.Compunent.Parser;

import com.Library.Compunent.Exception.Route;
import com.Library.Compunent.Reflection.Reflection;
import com.Library.Controller.Center;
import com.Library.Models.Borrow;
import com.Library.Models.Category;
import com.Library.Models.Library;
import com.Library.Models.Resources.Book;
import com.Library.Models.Resources.GanjineBook;
import com.Library.Models.Resources.SellingBook;
import com.Library.Models.Resources.Thesis;
import com.Library.Models.Users.Manager;
import com.Library.Models.Users.Professor;
import com.Library.Models.Users.Staff;
import com.Library.Models.Users.Student;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    private final Center center;
    private final Route router;

    public Parser() {
        center = new Center();
        router = new Route();
    }

    public void parsCommand(String input) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String[] command = input.split("#");
        if (command.length < 2) {
            System.out.println("invalid input");
            return;
        }
        String[] args = command[1].split("\\|");
        router.routing(command[0],args);
    }

    public void addLibrary(String[] args) {
        Library library = new Library(args[2], args[3], args[4], Integer.parseInt(args[5]), args[6]);
        System.out.println(center.addLibrary(args[0], args[1], library));
    }

    public void addCategory(String[] args) {
        Category category = new Category(args[2], args[3], args[4]);
        System.out.println(center.addCategory(args[0], args[1], category));
    }

    public void addStudent(String[] args) {
        Student student = new Student(args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
        System.out.println(center.addUser(args[0], args[1], student));
    }

    public void addStaff(String[] args) {
        if (args[9].equals("staff")) {
            Staff staff = new Staff(args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
            System.out.println(center.addUser(args[0], args[1], staff));
        } else {
            Professor professor = new Professor(args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
            System.out.println(center.addUser(args[0], args[1], professor));
        }
    }

    public void addManager(String[] args) {
        Manager manager = new Manager(args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
        System.out.println(center.addUser(args[0], args[1], manager));
    }

    public void removeUser(String[] args) {
        System.out.println(center.removeUser(args[0], args[1], args[2]));
    }

    public void addBook(String[] args) {
        Book book = new Book(args[2], args[3], args[4], args[8], args[9], args[5], Integer.parseInt(args[7]), args[6]);
        System.out.println(center.addResource(args[0], args[1], book));
    }

    public void addThesis(String[] args) {
        Thesis thesis = new Thesis(args[2], args[3], args[4], args[7], args[8], args[4], args[6]);
        System.out.println(center.addResource(args[0], args[1], thesis));
    }

    public void addGanjine(String[] args) {
        GanjineBook ganjineBook = new GanjineBook(args[2], args[3], args[4], args[8], args[9], args[5], args[6], args[7]);
        System.out.println(center.addResource(args[0], args[1], ganjineBook));
    }

    public void addSellingBook(String[] args) {
        SellingBook sellingBook = new SellingBook(args[2], args[3], args[4], args[10], args[11], args[5], Integer.parseInt(args[7]), args[6], args[8], args[9]);
        System.out.println(center.addResource(args[0], args[1], sellingBook));
    }

    public void removeResource(String[] args) {
        System.out.println(center.removeResource(args[0], args[1], args[2], args[3]));
    }

    public void borrow(String[] args) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(args[4] + " " + args[5]);
        Borrow borrow = new Borrow(args[0], args[3], args[2], date);
        System.out.println(center.borrow(borrow, args[1]));
    }

    public void returning(String[] args) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(args[4] + " " + args[5]);
        Borrow borrow = new Borrow(args[0], args[3], args[2], date);
        System.out.println(center.returning(borrow, args[1]));
    }

    public void buy(String[] args) {
        System.out.println(center.buy(args[0], args[1], args[2], args[3]));
    }

    public void read(String[] args) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(args[4] + " " + args[5]);
        System.out.println(center.read(args[0], args[1], args[2], args[3], date));
    }

    public void addComment(String[] args) {
        System.out.println(center.addComment(args[0], args[1], args[2], args[3], args[4]));
    }

    public void search(String[] args) {
        System.out.println(center.search(args[0]));
    }

    public void searchUser(String[] args) {
        System.out.println(center.searchUser(args[0], args[1], args[2]));
    }

    public void reportPenalties(String[] args) {
        System.out.println(center.reportPenalties(args[0], args[1]));
    }

    public void reportPassedDeadLine(String[] args) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(args[3] + " " + args[4]);
        System.out.println(center.reportPassedDeadLine(args[0], args[1], args[2], date));
    }

    public void libraryReport(String[] args) {
        System.out.println(center.libraryReport(args[0], args[1], args[2]));
    }

    public void categoryReport(String[] args) {
        System.out.println(center.categoryReport(args[0], args[1], args[2], args[3]));
    }

    public void reportMostPopular(String[] args) {
        System.out.println(center.reportMostPopular(args[0], args[1], args[2]));
    }

    public void reportSelling(String[] args) {
        System.out.println(center.reportSelling(args[0], args[1], args[2]));
    }
}

