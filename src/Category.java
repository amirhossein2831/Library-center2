import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Category {
    private final String id;
    private final String name;
    private final String parentId;
    private ArrayList<String> subs;
    public Category(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        subs = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }

    public ArrayList<String> getSubs() {
        return subs;
    }
    public void setSubs(String sub) {
        this.subs.add(sub);
    }
}
