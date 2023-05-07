import java.util.ArrayList;
import java.util.HashSet;

public class Admin extends User implements Buy,SearchUser {
    private ArrayList<SellingBook> buys;
    public Admin(String id, String pass, String firstName, String lastName, String nationalId, String year, String address) {
        super(id, pass, firstName, lastName, nationalId, year, address);
        buys = new ArrayList<>();
    }
    public void buy(SellingBook sellingBook) {
        buys.add(sellingBook);
    }

    @Override
    public HashSet<String> searchUser(ArrayList<User> users,String key) {
        HashSet<String> value = new HashSet<>();
        for (User user : users) {
            if (user.getFirstName().equalsIgnoreCase(key)) {
                value.add(user.getId());
            }
            else if (user.getLastName().equalsIgnoreCase(key)) {
                value.add(user.getId());
            }
        }
        return value;
    }

}
