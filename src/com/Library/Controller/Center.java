package com.Library.Controller;

import com.Library.Models.Borrow;
import com.Library.Models.Category;
import com.Library.Models.Interface.Buy;
import com.Library.Models.Interface.Comment;
import com.Library.Models.Interface.Read;
import com.Library.Models.Interface.SearchUser;
import com.Library.Models.Library;
import com.Library.Models.Resources.GanjineBook;
import com.Library.Models.Resources.Resource;
import com.Library.Models.Resources.SellingBook;
import com.Library.Models.Users.*;
import com.Library.Compunent.Rule.Rule;

import java.util.*;

public class Center {
    private final HashMap<String, Library> libraries;
    private final HashMap<String, Category> categories;
    private HashMap<String, User> users;
    public Center() {
        libraries = new HashMap<>();
        categories = new HashMap<>();
        users = new HashMap<>();
        Admin admin = new Admin("admin", "AdminPass", "AmirHossein", "Motaghian", "000000000", "19", "Tehran,AUT");
        users.put(admin.getId(), admin);
    }
    public String addLibrary(String adminId, String adminPass, Library library) {
        Rule rule = new Rule(adminId, adminPass, users);
        if (libraries.get(library.getId()) != null) {
            return "duplicate-id";
        }
        libraries.put(library.getId(), library);
        return "success";
    }
    public String addCategory(String adminId, String adminPass, Category category) {
        Rule rule = new Rule(adminId, adminPass, users);
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
    public String addUser(String adminId, String adminPass, User user) {
        Rule rule = new Rule(adminId, adminPass, users);
        return checkUser(user);
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
    public String removeUser(String adminId, String adminPass, String id) {
        Rule rule = new Rule(adminId, adminPass, users);
        if (users.get(id) == null) {
            return "not-found";
        }
        if (users.get(id).getDebt() != 0) {
            return "not-allowed";
        }
        if (countBorrow(id) != 0) {
            return "not-allowed";
        }
        users.remove(id);
        return "success";
    }
    public String addResource(String managerId, String managerPass, Resource resource) {
        Rule rule = new Rule(managerId, managerPass, resource.getLibraryId(), users, libraries);
        return checkResource(resource);
    }
    private String checkResource(Resource resource) {
        Library library = libraries.get(resource.getLibraryId());
        if (library == null) {
            return "not-found";
        }
        if (library.getResources().get(resource.getId()) != null) {
            return "duplicate-id";
        }
        if (!resource.getCategoryId().equals("null") && categories.get(resource.getCategoryId()) == null) {
            return "not-found";
        }
        library.getResources().put(resource.getId(), resource);
        return "success";
    }
    public String removeResource(String managerId, String managerPass, String resourceID, String libraryId) {
        Rule rule = new Rule(managerId, managerPass,libraryId, users, libraries);
        if (rule.getLibrary().getResources().get(resourceID) == null) {
            return "not-found";
        }
        if (rule.getLibrary().getBorrows().get(resourceID) != null) {
            return "not-allowed";
        }
        rule.getLibrary().getResources().remove(resourceID);
        return "success";
    }
    private int countBorrow(String userId) {
        int x = 0;
        for (Library library : libraries.values()) {
            x += library.countBorrow(userId);
        }
        return x;
    }
    private boolean checkDelay(Borrow borrow, Resource resource, User user) {
        for (Library library : libraries.values()) {
            if (library.hasDelay(borrow, resource, user, borrow.getUserId())) {
                return true;
            }
        }
        return false;
    }
    public String borrow(Borrow borrow, String userPass) {
        Rule rule = new Rule(borrow.getUserId(), userPass, borrow.getLibraryId(), borrow.getResourceId(), users, libraries);
        if (rule.getUser().getDebt() != 0) {
            return "not-allowed";
        }
        if (checkDelay(borrow, rule.getResource(), rule.getUser())) {
            return "not-allowed";                               //the user has delay
        }
        if (!rule.getLibrary().borrow(borrow, countBorrow(borrow.getUserId()), rule.getUser(), rule.getResource())) {
            return "not-allowed";
        }
        return "success";
    }
    public String returning(Borrow borrow, String userPass) {
        Rule rule = new Rule(borrow.getUserId(), userPass, borrow.getLibraryId(), borrow.getResourceId(), users, libraries);
        int hold = rule.getLibrary().returning(borrow, rule.getResource(),rule.getUser());
        if (hold < 0) {
            return "not-found";
        } else if (hold > 0) {
            return "" + hold;
        }
        return "success";
    }
    public String buy(String userId, String pass, String libraryId, String resourceId) {
        Rule rule = new Rule(userId, pass, libraryId, resourceId, users, libraries);
        if (!(rule.getResource() instanceof SellingBook)) {
            return "not-allowed";
        }
        if (rule.getUser() instanceof Manager) {
            return "permission-denied";
        }
        if (rule.getUser().getDebt() != 0) {
            return "not-allowed";
        }
        if (rule.getResource().getRealNum() == 0) {
            return "not-allowed";
        }
        Buy action = (Buy) rule.getUser();
        action.buy((SellingBook) rule.getResource());
        rule.getResource().decreaseRealNum();
        return "success";
    }
    public String read(String userId, String pass, String libraryId, String resourceId, Date date) {
        Rule rule = new Rule(userId, pass, libraryId, resourceId, users, libraries);
        if (!(rule.getResource() instanceof GanjineBook)) {
            return "not-allowed";
        }
        if (!(rule.getUser() instanceof Professor)) {
            return "permission-denied";
        }
        if (rule.getUser().getDebt() != 0) {
            return "not-allowed";
        }
        Read action = (Read) rule.getUser();
        if (!(action.read((GanjineBook) rule.getResource(), date))) {
            return "not-allowed";
        }
        return "success";
    }
    public String addComment(String userId, String pass, String libraryId, String resourceId, String comment) {
        Rule rule = new Rule(userId, pass, libraryId, resourceId, users, libraries);
        if (rule.getUser() instanceof Manager) {
            return "permission-denied";
        }
        if (!(rule.getUser() instanceof Student || rule.getUser() instanceof Professor)) {
            return "permission-denied";
        }
        Comment com = (Comment) rule.getUser();
        com.addComment(comment, rule.getResource());
        return "success";
    }
    public StringBuilder search(String key) {
        StringBuilder str = new StringBuilder();
        HashSet<StringBuilder> values = new HashSet<>();
        for (Library library : libraries.values()) {
            for (Resource resource : library.getResources().values()) {
                StringBuilder s = resource.search(key);
                if (s.length() != 0) {
                    values.add(resource.search(key));
                }
            }
        }
        ArrayList<StringBuilder> hold = new ArrayList<>(values);
        Collections.sort(hold);
        for (StringBuilder temp : hold) {
            str.append(temp);
            str.append("|");
        }
        if (str.length() != 0) {
            str.deleteCharAt(str.length() - 1);
            return str;
        }
        return new StringBuilder("not-found");
    }
    public StringBuilder searchUser(String userId, String pass, String key) {
        User user = users.get(userId);
        StringBuilder str = new StringBuilder();
        HashSet<String> values ;
        if (user == null) {
            return new StringBuilder("not-found");
        } else if (!user.getPass().equals(pass)) {
            return new StringBuilder("invalid-pass");
        }
        if (user instanceof Student) {
            return new StringBuilder("permission-denied");
        }
        Collection <User> collection = users.values();
        ArrayList<User> usersHold = new ArrayList<>(collection);
        values = ((SearchUser) user).searchUser(usersHold,key);
        if (values == null) {
            return new StringBuilder("not-found");
        }
        ArrayList<String> hold = new ArrayList<>(values);
        Collections.sort(hold);
        for (String temp : hold) {
            str.append(temp);
            str.append("|");
        }
        if (str.length() != 0) {
            str.deleteCharAt(str.length() - 1);
        }
        return str;
    }
    public String reportPenalties(String userId, String pass) {
        User user = users.get(userId);
        if (user == null) {
            return "not-found";
        } else if (!user.getPass().equals(pass)) {

            return "invalid-pass";
        }
        if (!(user instanceof Admin)) {
            return "permission-denied";
        }
        int allDebt = 0;
        for (User u : users.values()) {
            allDebt += u.getDebt();
        }
        return "" + allDebt;
    }
    public StringBuilder reportPassedDeadLine(String userId, String pass, String libraryId, Date date) {
        Rule rule = new Rule(userId, pass, libraryId, users, libraries);
       return rule.getLibrary().reportPassedDeadLine(date,users);
    }
    public String libraryReport(String userId, String pass, String libraryId) {
        Rule rule = new Rule(userId, pass, libraryId, users, libraries);
        return rule.getLibrary().libraryReport();
    }
    public String categoryReport(String userId,String pass,String categoryId,String libraryId) {
        Rule rule = new Rule(userId, pass, libraryId, users, libraries);
        int [] hold = rule.getLibrary().categoryReport(categories,categoryId);
        if (hold == null) {
            return "not-found";
        }
        return hold[3] + " " + hold[0] + " " + hold[1] + " " + hold[2];
    }
    public String reportMostPopular(String userId, String pass, String libraryId) {
        Rule rule = new Rule(userId, pass, libraryId, users, libraries);
        return rule.getLibrary().reportMostPopular();
    }
    public String reportSelling(String userId, String pass, String libraryId) {
        Rule rule = new Rule(userId, pass, libraryId, users, libraries);
        return rule.getLibrary().reportSelling(users, libraryId);
    }
}