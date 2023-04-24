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
        String answer = this.admin.isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
        if (libraries.get(library.getId()) != null) {
            return "duplicate-id";
        }
        libraries.put(library.getId(), library);
        return "success";
    }
    public String addCategory(String adminId,String adminPass,Category category) {
        User admin = users.get(adminId);
        String answer = this.admin.isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
        if (categories.get(category.getParentId()) == null) {
            return "nou-found";
        }
        if (categories.get(category.getId()) != null) {
            return "duplicate-id";
        }
        categories.put(category.getId(), category);
        return "success";
    }
    public String addStudent(String adminId,String adminPass,Student student) {
        User admin = users.get(adminId);
        String answer = this.admin.isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
        if (users.get(student.getId()) != null) {
            return "duplicate-id";
        }
        users.put(student.getId(), student);
        return "success";
    }
    public String addStaff(String adminId,String adminPass,Staff staff) {
        User admin = users.get(adminId);
        String answer = this.admin.isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
        if (users.get(staff.getId()) != null) {
            return "duplicate-id";
        }
        users.put(staff.getId(), staff);
        return "success";
    }
    public String addProfessor(String adminId, String adminPass, Professor professor) {
        User admin = users.get(adminId);
        String answer = this.admin.isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
        if (users.get(professor.getId()) != null) {
            return "duplicate-id";
        }
        users.put(professor.getId(), professor);
        return "success";
    }
    public String addManager(String adminId,String adminPass,Manager manager) {
        User admin = users.get(adminId);
        String answer = this.admin.isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
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
