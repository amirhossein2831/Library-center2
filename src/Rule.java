import java.util.HashMap;

public class Rule {
    private User user;
    private HashMap<String, User> users;
    private Library library;
    private HashMap<String, Library> libraries;
    private Resource resource;
  

    public User getUser() {
        return user;
    }
    public Library getLibrary() {
        return library;
    }
    public Resource getResource() {
        return resource;
    }
    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }
    public void setLibraries(HashMap<String, Library> libraries) {
        this.libraries = libraries;
    }
}
