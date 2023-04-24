import java.util.HashMap;

public class Center {
    private HashMap<String, Library> libraries;
    private HashMap<String, Category> categories;
    private HashMap<String, User> users;
    private Admin admin;

    public Center() {
        libraries = new HashMap<>();
        categories = new HashMap<>();
        admin = new Admin("admin", "AdminPass", "AmirHossein", "Motaghian", "000000000", "19", "Tehran,AUT");
        users = new HashMap<>();
    }
    public String addLibrary(String adminId, String adminPass, Library library) {
        User admin = users.get(adminId);
        if (admin == null) {
            return "not-found";
        } else if (!(admin instanceof Admin)) {
            return "permission-denied";
        }
        if (!((Admin) admin).getPass().equals(adminPass)) {
            return "invalid-pass";
        }if (libraries.get(library.getId()) != null) {
            return "duplicate-id";
        }
        libraries.put(library.getId(), library);
        return "success";
    }
    //TODO this need more condition about the admin
    public String addCategory(Category category) {
        if (categories.get(category.getId()) != null) {
            return "duplicate-id";
        }
        categories.put(category.getId(), category);
        return "success";
    }
    //TODO this need more condition about the admin
    public String addStudent(Student student) {
        if (users.get(student.getId()) != null) {
            return "duplicate-id";
        }
        users.put(student.getId(), student);
        return "success";
    }
    //TODO this need more condition about the admin
    public String addStaff(Staff staff) {
        if (users.get(staff.getId()) != null) {
            return "duplicate-id";
        }
        users.put(staff.getId(), staff);
        return "success";
    }
    //TODO this need more condition about the admin
    public String addManager(Manager manager) {
        if (users.get(manager.getId()) != null) {
            return "duplicate-id";
        }
        if (libraries.get(manager.getLibraryId()) == null) {
            return "not-found";
        }
        users.put(manager.getId(), manager);
        return "success";
    }
}
