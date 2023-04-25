import java.util.HashMap;

public class Library {
    private String id;
    private String name;
    private String year;
    private int numDesk;
    private String address;
    private HashMap<String, Resource> resources;

    public Library(String id, String name, String year, int numDesk, String address) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.numDesk = numDesk;
        this.address = address;
        resources = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public int getNumDesk() {
        return numDesk;
    }

    public String getAddress() {
        return address;
    }

    public HashMap<String, Resource> getResources() {
        return resources;
    }
}
