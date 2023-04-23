public class Library {
    private String id;
    private String name;
    private String year;
    private int numDesk;
    private String address;

    public Library(String id, String name, String year, int numDesk, String address) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.numDesk = numDesk;
        this.address = address;
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

}
