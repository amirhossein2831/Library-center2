package com.Library.Controller;

import com.Library.Compunent.Rule.Rule;
import com.Library.Models.Category;
import com.Library.Models.Library;
import com.Library.Models.Users.*;

public class AdminController extends BaseController {
    public AdminController() {
        super();
    }

    public String addLibrary(String[] args) {
        Library library = new Library(args[2], args[3], args[4], Integer.parseInt(args[5]), args[6]);
        Rule rule = new Rule(args[0], args[1], users);
        if (libraries.get(library.getId()) != null) {
            return "duplicate-id";
        }
        libraries.put(library.getId(), library);
        return "success";
    }

    public String addCategory(String[] args) {
        Category category = new Category(args[2], args[3], args[4]);
        Rule rule = new Rule(args[0], args[1], users);
        if (categories.get(category.getId()) != null) {
            return "duplicate-id";
        }
        Category parentCat = categories.get(category.getParentId());
        if (!category.getParentId().equals("null") && parentCat == null) {
            return "not-found";
        }
        if (category.getParentId().equals("null")) {
            categories.put(category.getId(), category);
            return "success";
        }
        parentCat.setSubs(category.getId());
        categories.put(category.getId(), category);
        return "success";
    }


    public String addStudent(String[] args) {
        Student student = new Student(args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
        Rule rule = new Rule(args[0], args[1], users);
        return checkUser(student);
    }

    private String checkUser(User user) {
        if (user instanceof Manager) {
            Manager manager = (Manager) user;
            if (users.get(manager.getId()) != null) {
                return "duplicate-id";
            }
            if (libraries.get(manager.getLibraryId()) == null) {
                return "not-found";
            }
            users.put(manager.getId(), manager);
            return "success";
        }
        if (users.get(user.getId()) != null) {
            return "duplicate-id";
        }
        users.put(user.getId(), user);
        return "success";
    }

    public String addStaff(String[] args) {
        if (args[9].equals("staff")) {
            Staff staff = new Staff(args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
            Rule rule = new Rule(args[0], args[1], users);
            return checkUser(staff);
        } else {
            Professor professor = new Professor(args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
            Rule rule = new Rule(args[0], args[1], users);
            return checkUser(professor);
        }
    }

    public String addManager(String[] args) {
        Manager manager = new Manager(args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
        Rule rule = new Rule(args[0], args[1], users);
        return checkUser(manager);
    }

    public String removeUser(String[] args) {
        Rule rule = new Rule(args[0], args[1], users);
        if (users.get(args[2]) == null) {
            return "not-found";
        }
        if (users.get(args[2]).getDebt() != 0) {
            return "not-allowed";
        }
        if (countBorrow(args[2]) != 0) {
            return "not-allowed";
        }
        users.remove(args[2]);
        return "success";
    }
}
