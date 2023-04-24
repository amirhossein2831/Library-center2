public class User {
    private String firstName;
    private String lastName;
    private String nationalId;
    private String year;
    private String address;

    public User(String firstName, String lastName, String nationalId, String year, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.year = year;
        this.address = address;
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
