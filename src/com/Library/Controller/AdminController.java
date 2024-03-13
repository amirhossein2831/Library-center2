package com.Library.Controller;

import com.Library.Models.Category;
import com.Library.Models.Library;
import com.Library.Models.LibraryCenter;
import com.Library.Models.Users.*;

public class AdminController extends BaseController {

    public String addLibrary(String[] args) {
        Library library = new Library(args[2], args[3], args[4], Integer.parseInt(args[5]), args[6]);
        if (LibraryCenter.getLibraries().get(library.getId()) != null) {
            return "duplicate-id";
        }
        LibraryCenter.getLibraries().put(library.getId(), library);
        return "success";
    }

    public String addCategory(String[] args) {
        Category category = new Category(args[2], args[3], args[4]);
        if (LibraryCenter.getCategories().get(category.getId()) != null) {
            return "duplicate-id";
        }
        Category parentCat = LibraryCenter.getCategories().get(category.getParentId());
        if (!category.getParentId().equals("null") && parentCat == null) {
            return "not-found";
        }
        if (category.getParentId().equals("null")) {
            LibraryCenter.getCategories().put(category.getId(), category);
            return "success";
        }
        parentCat.setSubs(category.getId());
        LibraryCenter.getCategories().put(category.getId(), category);
        return "success";
    }

    public String addStudent(String[] args) {
        Student student = new Student(args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
        return checkUser(student);
    }

    private String checkUser(User user) {
        if (user instanceof Manager) {
            Manager manager = (Manager) user;
            if (LibraryCenter.getUsers().get(manager.getId()) != null) {
                return "duplicate-id";
            }
            if (LibraryCenter.getLibraries().get(manager.getLibraryId()) == null) {
                return "not-found";
            }
            LibraryCenter.getUsers().put(manager.getId(), manager);
            return "success";
        }
        if (LibraryCenter.getUsers().get(user.getId()) != null) {
            return "duplicate-id";
        }
        LibraryCenter.getUsers().put(user.getId(), user);
        return "success";
    }

    public String addStaff(String[] args) {
        if (args[9].equals("staff")) {
            Staff staff = new Staff(args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
            return checkUser(staff);
        } else {
            Professor professor = new Professor(args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
            return checkUser(professor);
        }
    }

    public String addManager(String[] args) {
        Manager manager = new Manager(args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
        return checkUser(manager);
    }

    public String removeUser(String[] args) {
        if (LibraryCenter.getUsers().get(args[2]) == null) {
            return "not-found";
        }
        if (LibraryCenter.getUsers().get(args[2]).getDebt() != 0) {
            return "not-allowed";
        }
        if (countBorrow(args[2]) != 0) {
            return "not-allowed";
        }
        LibraryCenter.getUsers().remove(args[2]);
        return "success";
    }

    public String reportPenalties(String[] args) {
        int allDebt = 0;
        for (User u : LibraryCenter.getUsers().values()) {
            allDebt += u.getDebt();
        }
        return "" + allDebt;
    }
}
