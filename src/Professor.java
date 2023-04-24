public class Professor extends User{
    private String id;
    private String pass;

    public Professor( String id, String pass,String firstName, String lastName, String nationalId, String year, String address) {
        super(firstName, lastName, nationalId, year, address);
        this.id = id;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }
}
