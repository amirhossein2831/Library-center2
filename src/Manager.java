public class Manager extends User {
    private String id;
    private String pass;
    private String libraryId;

    public Manager(String id, String pass,String firstName, String lastName, String nationalId, String year, String address,String libraryId) {
        super(firstName, lastName, nationalId, year, address);
        this.id = id;
        this.pass = pass;
        this.libraryId = libraryId;
    }
}
