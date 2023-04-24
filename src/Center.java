import java.util.HashMap;

public class Center {
    private HashMap<String, Library> libraries;
    private HashMap<String, Category> categories;
    private Admin admin;

    public Center() {
        libraries = new HashMap<>();
        categories = new HashMap<>();
        admin = new Admin("admin", "AdminPass", "AmirHossein", "Motaghian", "000000000", "19", "Tehran,AUT");
    }

    public HashMap<String, Library> getLibraries() {
        return libraries;
    }
    //TODO this need more condition about the admin
    public String addLibrary(Library library) {
        if (libraries.get(library.getId()) != null) {
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
}
