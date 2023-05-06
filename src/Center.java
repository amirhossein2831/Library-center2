import java.util.HashMap;

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
        if (!category.getParentId().equals("null") && categories.get(category.getParentId()) == null) {
            return "not-found";
        }
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

    //TODO need another condition to check user have any book or not
    public String removeUser(String adminId, String adminPass, String id) {
        User admin = users.get(adminId);
        String answer = isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
        if (users.get(id) == null) {
            return "not-found";
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

    //TODO need another condition to check user have any book or not
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
        if (user == null) {                                      //user not-found
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
        int hold = library.returning();
        if (hold < 0) {
            return "not-found";
        } else if (hold > 0) {
            return "" + hold;
        }
        return "success";
    }
}
