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
    public String addUser(String adminId, String adminPass, User user) {
        User admin = users.get(adminId);
        String answer = this.admin.isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
        if (user instanceof Student) {
            Student student = (Student) user;
            return addStudent(student);

        } else if (user instanceof Staff) {
            Staff staff = (Staff) user;
            return addStaff(staff);

        } else if (user instanceof Professor) {
            Professor professor = (Professor) user;
            return addProfessor(professor);
        }
        Manager manager = (Manager) user;
        return addManager(manager);
    }
    private String addStudent(Student student) {
        if (users.get(student.getId()) != null) {
            return "duplicate-id";
        }
        users.put(student.getId(), student);
        return "success";
    }
    private String addStaff(Staff staff) {
        if (users.get(staff.getId()) != null) {
            return "duplicate-id";
        }
        users.put(staff.getId(), staff);
        return "success";
    }
    private String addProfessor( Professor professor) {
        if (users.get(professor.getId()) != null) {
            return "duplicate-id";
        }
        users.put(professor.getId(), professor);
        return "success";
    }
    private String addManager(Manager manager) {
        if (users.get(manager.getId()) != null) {
            return "duplicate-id";
        }
        if (libraries.get(manager.getLibraryId()) == null) {
            return "not-found";
        }
        users.put(manager.getId(), manager);
        return "success";
    }
    //TODO need another condition to check user have any book or not
    public String removeUser(String adminId, String adminPass, String id) {
        User admin = users.get(adminId);
        String answer = this.admin.isAdmin(admin, adminPass);
        if (answer != null) {
            return answer;
        }
        if (users.get(id) == null) {
            return "not-found";
        }
        users.remove(id);
        return "success";
    }
}
