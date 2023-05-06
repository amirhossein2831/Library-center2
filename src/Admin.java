import java.util.ArrayList;

public class Admin extends User {
    private ArrayList<SellingBook> buys;
    public Admin(String id, String pass, String firstName, String lastName, String nationalId, String year, String address) {
        super(id, pass, firstName, lastName, nationalId, year, address);
        buys = new ArrayList<>();
    }
    public void buy(SellingBook sellingBook) {
        buys.add(sellingBook);
    }

}
