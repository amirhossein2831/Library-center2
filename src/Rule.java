import java.util.HashMap;

public class Rule {
    private User user;
    private HashMap<String, User> users;
    private Library library;
    private HashMap<String, Library> libraries;
    private Resource resource;

    private void isManager(String userId, String pass, String libraryId) {
        if (users.get(userId) == null) {
            throw new NotFoundException();
        } else if (!users.get(userId).getPass().equals(pass)) {
            throw new InvalidPassException();
        }
        if (libraries.get(libraryId) == null) {
            throw new NotFoundException();
        }
        if (!(users.get(userId) instanceof Manager)) {
            throw new PermissionDeniedException();
        }
        if (!((Manager) users.get(userId)).getLibraryId().equals(libraryId)) {
            throw new PermissionDeniedException();
        }
    }
    private void isAdmin(String userId, String adminPass) {
        if (users.get(userId) == null) {
            throw new NotFoundException();
        } else if (!(users.get(userId) instanceof Admin)) {
            throw new PermissionDeniedException();
        }
        if (!users.get(userId).getPass().equals(adminPass)) {
            throw new InvalidPassException();
        }
    }
    private void checkLibrary(String userId, String pass, String libraryId, String resourceId) {
        if (users.get(userId) == null) {
            throw new NotFoundException();
        } else if (!users.get(userId).getPass().equals(pass)) {
            throw new InvalidPassException();
        }
        if (libraries.get(libraryId) == null) {
            throw new NotFoundException();
        }
        if (libraries.get(libraryId).getResource(resourceId) == null) {
            throw new NotFoundException();
        }
    }
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
