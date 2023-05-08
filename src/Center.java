import java.awt.*;
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
        User admin = users.get(adminId);
        String answer = isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
        if (libraries.get(library.getId()) != null) {
            return "duplicate-id";
        }
        libraries.put(library.getId(), library);
        return "success";
    }

    public String addCategory(String adminId, String adminPass, Category category) {
        User admin = users.get(adminId);
        String answer = isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
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
        User admin = users.get(adminId);
        String answer = isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
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
        User admin = users.get(adminId);
        String answer = isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
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
        User manager = users.get(managerId);
        String answer = isManager(manager, managerPass, resource.getLibraryId());
        if (answer != null) {
            return answer;
        }
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
        User manager = users.get(managerId);
        String answer = isManager(manager, managerPass, libraryId);
        if (answer != null) {
            return answer;
        }
        Library library = libraries.get(libraryId);
        if (library == null) {
            return "not-found";
        }
        if (library.getResources().get(resourceID) == null) {
            return "not-found";
        }
        if (library.getBorrows().get(resourceID) != null) {
            return "not-allowed";
        }
        library.getResources().remove(resourceID);
        return "success";
    }

    private String isManager(User manager, String managerPass, String libraryId) {
        if (manager == null) {
            return "not-found";
        } else if (!(manager instanceof Manager)) {
            return "permission-denied";
        }
        if (!manager.getPass().equals(managerPass)) {
            return "invalid-pass";
        }
        if (libraries.get(libraryId) == null) {
            return "not-found";
        }
        if (!((Manager) manager).getLibraryId().equals(libraryId)) {
            return "permission-denied";
        }
        return null;
    }

    private String isAdmin(User admin, String adminPass) {
        if (admin == null) {
            return "not-found";
        } else if (!(admin instanceof Admin)) {
            return "permission-denied";
        }
        if (!admin.getPass().equals(adminPass)) {
            return "invalid-pass";
        }
        return null;
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
        User user = users.get((borrow.getUserId()));
        if (user == null) {                                      //user not-found
            return "not-found";
        } else if (!user.getPass().equals(userPass)) {           //invalid pass
            return "invalid-pass";
        }
        if (user.getDebt() != 0) {
            return "not-allowed";
        }
        Library library = libraries.get(borrow.getLibraryId());  //library not-found
        if (library == null) {
            return "not-found";
        }
        Resource resource = library.getResource(borrow.getResourceId());
        if (resource == null) {                                  //resource not-found
            return "not-found";
        }
        if (checkDelay(borrow, resource, user)) {
            return "not-allowed";                               //the user has delay
        }
        if (!library.borrow(borrow, countBorrow(borrow.getUserId()), user, resource)) {
            return "not-allowed";
        }
        return "success";
    }

    public String returning(Borrow borrow, String userPass) {
        User user = users.get((borrow.getUserId()));
        if (user == null) {                             //user not-found
            return "not-found";
        } else if (!user.getPass().equals(userPass)) {           //invalid pass
            return "invalid-pass";
        }
        Library library = libraries.get(borrow.getLibraryId());  //library not-found
        if (library == null) {
            return "not-found";
        }
        Resource resource = library.getResource(borrow.getResourceId());
        if (resource == null) {                                  //resource not-found
            return "not-found";
        }
        int hold = library.returning(borrow, resource,user);
        if (hold < 0) {
            return "not-found";
        } else if (hold > 0) {
            return "" + hold;
        }
        return "success";
    }

    public String buy(String userId, String pass, String libraryId, String resourceId) {
        User user = users.get(userId);
        if (user == null) {
            return "not-found";
        } else if (!user.getPass().equals(pass)) {
            return "invalid-pass";
        }
        Library library = libraries.get(libraryId);
        if (library == null) {
            return "not-found";
        }
        Resource resource = library.getResource(resourceId);
        if (resource == null) {
            return "not-found";
        }
        if (!(resource instanceof SellingBook)) {
            return "not-allowed";
        }
        if (user instanceof Manager) {
            return "permission-denied";
        }
        if (user.getDebt() != 0) {
            return "not-allowed";
        }
        if (resource.getNumber() == 0) {
            return "not-allowed";
        }
        Buy action = (Buy) user;
        action.buy((SellingBook) resource);
        resource.decreaseNumber();
        resource.decreaseRealNum();
        return "success";
    }

    public String read(String userId, String pass, String libraryId, String resourceId, Date date) {
        User user = users.get(userId);
        if (user == null) {
            return "not-found";
        } else if (!user.getPass().equals(pass)) {
            return "invalid-pass";
        }
        Library library = libraries.get(libraryId);
        if (library == null) {
            return "not-found";
        }
        Resource resource = library.getResource(resourceId);
        if (resource == null) {
            return "not-found";
        }
        if (!(resource instanceof GanjineBook)) {
            return "not-allowed";
        }
        if (!(user instanceof Professor)) {
            return "permission-denied";
        }
        if (user.getDebt() != 0) {
            return "not-allowed";
        }
        Read action = (Read) user;
        if (!(action.read((GanjineBook) resource, date))) {
            return "not-allowed";
        }
        return "success";
    }

    public String addComment(String userId, String pass, String libraryId, String resourceId, String comment) {
        User user = users.get(userId);
        if (user == null) {
            return "not-found";
        } else if (!user.getPass().equals(pass)) {
            return "invalid-pass";
        }
        Library library = libraries.get(libraryId);
        if (library == null) {
            return "not-found";
        }
        Resource resource = library.getResource(resourceId);
        if (resource == null) {
            return "not-found";
        }
        if (user instanceof Manager) {
            return "permission-denied";
        }
        if (!(user instanceof Student || user instanceof Professor)) {
            return "permission-denied";
        }
        Comment com = (Comment) user;
        com.addComment(comment, resource);
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
        User user = users.get(userId);
        if (user == null) {
            return new StringBuilder("not-found");
        } else if (!user.getPass().equals(pass)) {
            return new StringBuilder("invalid-pass");
        }
        Library library = libraries.get(libraryId);
        if (library == null) {
            return new StringBuilder("not-found");
        }
        if (!(user instanceof Manager)) {
            return new StringBuilder("permission-denied");
        }
        if (!((Manager) user).getLibraryId().equals(libraryId)) {
            return new StringBuilder("permission-denied");
        }

       return library.reportPassedDeadLine(date,users);
    }

    public String libraryReport(String userId, String pass, String libraryId) {
        User user = users.get(userId);
        if (user == null) {
            return "not-found";
        } else if (!user.getPass().equals(pass)) {
            return "invalid-pass";
        }
        Library library = libraries.get(libraryId);
        if (library == null) {
            return "not-found";
        }
        if (!(user instanceof Manager)) {
            return "permission-denied";
        }
        if (!((Manager) user).getLibraryId().equals(libraryId)) {
            return "permission-denied";
        }
        return library.libraryReport();
    }

    public String categoryReport(String userId,String pass,String categoryId,String libraryId) {
        User user = users.get(userId);
        if (user == null) {
            return "not-found";
        } else if (!user.getPass().equals(pass)) {
            return "invalid-pass";
        }
        Library library = libraries.get(libraryId);
        if (library == null) {
            return "not-found";
        }

        if (!(user instanceof Manager)) {
            return "permission-denied";
        }
        if (!((Manager) user).getLibraryId().equals(libraryId)) {
            return "permission-denied";
        }
        int [] hold = library.categoryReport(categories,categoryId);
        if (hold == null) {
            return "not-found";
        }
        return hold[3] + " " + hold[0] + " " + hold[1] + " " + hold[2];
    }
}