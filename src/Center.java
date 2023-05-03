import java.util.ArrayList;
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
        if (!category.getParentId().equals("null") && categories.get(category.getParentId()) == null) {
            return "not-found";
        }
        if (categories.get(category.getId()) != null) {
            return "duplicate-id";
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

    //TODO need another condition to be checked
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

    public int numBorrowedBYUser(String userID) {
        int count = 0;
        for (Library library : libraries.values()) {
            for (ArrayList<Borrow> borrows : library.getBorrows().values()) {
                for (Borrow borrow : borrows) {
                    if (borrow.getUserId().equals(userID)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    public int numBorrowedOfBook(String resourceId) {
        int count = 0;
        for (Library library : libraries.values()) {
            count += library.getBorrows().get(resourceId).size();
        }
        return count;
    }

    public boolean checkIsBorrowed(String resourceId, String userID) {
        for (Library library : libraries.values()) {
            ArrayList<Borrow> borrows = library.getBorrows().get(resourceId);
            for (Borrow borrow : borrows) {
                if (borrow.getUserId().equals(userID)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String Borrow(Borrow borrow, String userPass) {
        User user = users.get((borrow.getUserId()));
        if (user == null) {             //user not-found
            return "not-found";
        } else if (!user.getPass().equals(userPass)) {//invalid pass
            return "invalid-pass";
        }
        Library library = libraries.get(borrow.getLibraryId());//library not-found
        if (library == null) {
            return "not-found";
        }
        Resource resource = library.getResources().get(borrow.getResourceId());
        if (resource == null) {                             //resource not-found
            return "not-found";
        }
        if (resource instanceof GanjineBook || resource instanceof SellingBook) {
            return "not-allowed";                         //if resource is a ganjineh or selling book
        }
        if (checkIsBorrowed(borrow.getResourceId(), borrow.getUserId())) {
            return "not-allowed";                                 //if he take this book already
        }
        //if(numBorrowedOfBook(borrow.getResourceId()) == library.getResources().get(borrow.getResourceId()).getNumber)//go and put num in resource and do it later
    }
}
