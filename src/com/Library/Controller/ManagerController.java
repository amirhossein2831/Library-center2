package com.Library.Controller;

import com.Library.Compunent.Rule.Rule;
import com.Library.Models.Library;
import com.Library.Models.LibraryCenter;
import com.Library.Models.Resources.*;

public class ManagerController extends BaseController {

    public String addBook(String[] args) {
        Book book = new Book(args[2], args[3], args[4], args[8], args[9], args[5], Integer.parseInt(args[7]), args[6]);
        Rule rule = new Rule(args[0], args[1], book.getLibraryId(), LibraryCenter.getUsers(), LibraryCenter.getLibraries());
        return checkResource(book);
    }

    public String addThesis(String[] args) {
        Thesis thesis = new Thesis(args[2], args[3], args[4], args[7], args[8], args[4], args[6]);
        Rule rule = new Rule(args[0], args[1], thesis.getLibraryId(), LibraryCenter.getUsers(), LibraryCenter.getLibraries());
        return checkResource(thesis);
    }

    public String addGanjine(String[] args) {
        GanjineBook ganjineBook = new GanjineBook(args[2], args[3], args[4], args[8], args[9], args[5], args[6], args[7]);
        Rule rule = new Rule(args[0], args[1], ganjineBook.getLibraryId(), LibraryCenter.getUsers(), LibraryCenter.getLibraries());
        return checkResource(ganjineBook);
    }

    public String addSellingBook(String[] args) {
        SellingBook sellingBook = new SellingBook(args[2], args[3], args[4], args[10], args[11], args[5], Integer.parseInt(args[7]), args[6], args[8], args[9]);
        Rule rule = new Rule(args[0], args[1], sellingBook.getLibraryId(), LibraryCenter.getUsers(), LibraryCenter.getLibraries());
        return checkResource(sellingBook);
    }

    public String removeResource(String[] args) {
        Rule rule = new Rule(args[0], args[1], args[3], LibraryCenter.getUsers(), LibraryCenter.getLibraries());
        if (rule.getLibrary().getResources().get(args[2]) == null) {
            return "not-found";
        }
        if (rule.getLibrary().getBorrows().get(args[2]) != null) {
            return "not-allowed";
        }
        rule.getLibrary().getResources().remove(args[2]);
        return "success";
    }

    private String checkResource(Resource resource) {
        Library library = LibraryCenter.getLibraries().get(resource.getLibraryId());
        if (library == null) {
            return "not-found";
        }
        if (library.getResources().get(resource.getId()) != null) {
            return "duplicate-id";
        }
        if (!resource.getCategoryId().equals("null") && LibraryCenter.getCategories().get(resource.getCategoryId()) == null) {
            return "not-found";
        }
        library.getResources().put(resource.getId(), resource);
        return "success";
    }
}
