public class Category {
    private String id;
    private String name;
    private String parentId;

    public Category(String id, String name,String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
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
}
