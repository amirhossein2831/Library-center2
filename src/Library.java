import java.util.HashMap;

public class Library {
    private final String id;
    private final String name;
    private final String year;
    private final int numDesk;
    private final String address;
    private final HashMap<String, Resource> resources;
    private HashMap<String, Borrow> borrows;

    public Library(String id, String name, String year, int numDesk, String address) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.numDesk = numDesk;
        this.address = address;
        resources = new HashMap<>();
        borrows = new HashMap<>();
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

    public HashMap<String, Borrow> getBorrows() {
        return borrows;
    }
}
