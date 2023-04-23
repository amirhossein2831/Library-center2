import java.util.HashMap;

public class Center {
    private HashMap<String, Library> libraries;

    public Center() {
        libraries = new HashMap<>();
    }
    //TODO this need more condition about the admin     
    public String addLibrary(Library library) {
        if (libraries.get(library.getId()) != null) {
            return "duplicate-id";
        }
        libraries.put(library.getId(), library);
        return "success";
    }
}
