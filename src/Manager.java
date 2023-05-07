public class Manager extends Staff  {
    private final String libraryId;

    public Manager(String id, String pass, String firstName, String lastName, String nationalId, String year, String address, String libraryId) {
        super(id, pass, firstName, lastName, nationalId, year, address);
        this.libraryId = libraryId;
    }
    public String getLibraryId() {
        return libraryId;
    }
}
