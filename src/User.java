public class User {
    private String id;
    private String pass;
    private String firstName;
    private String lastName;
    private String nationalId;
    private String year;
    private String address;

    public User(String id,String pass,String firstName, String lastName, String nationalId, String year, String address) {
        this.id = id;
        this.pass = pass;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.year = year;
        this.address = address;
    }
    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getYear() {
        return year;
    }

    public String getAddress() {
        return address;
    }
}
