public abstract class User {
    private final String id;
    private final String pass;
    private final String firstName;
    private final String lastName;
    private final String nationalId;
    private final String year;
    private final String address;
    private int debt;

    public User(String id, String pass, String firstName, String lastName, String nationalId, String year, String address) {
        this.id = id;
        this.pass = pass;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.year = year;
        this.address = address;
        debt = 0;
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

    public void setDebt(int debt) {
        this.debt += debt;
    }
}
