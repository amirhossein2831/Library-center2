package com.Library.Controller;

import com.Library.Models.Borrow;
import com.Library.Models.Resources.Book;
import com.Library.Models.Resources.GanjineBook;
import com.Library.Models.Resources.SellingBook;
import com.Library.Models.Resources.Thesis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    private final Center center;

    public Controller() {
        center = new Center();
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
