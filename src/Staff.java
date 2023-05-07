import java.util.ArrayList;

public class Staff extends User implements Buy {
    private ArrayList<SellingBook> buys;
    public Staff(String id, String pass, String firstName, String lastName, String nationalId, String year, String address) {
        super(id, pass, firstName, lastName, nationalId, year, address);
        buys = new ArrayList<>();
    }

    public void buy(SellingBook sellingBook) {
        buys.add(sellingBook);
    }
    @Override
    public void addComment(String comment,Resource resource) {
        resource.addComment(comment);
    }
}
