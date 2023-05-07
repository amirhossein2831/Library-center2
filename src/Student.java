import java.util.ArrayList;
import java.util.HashSet;

public class Student extends User implements Buy,Comment {
    private ArrayList<SellingBook> buys;

    public Student(String id, String pass, String firstName, String lastName, String nationalId, String year, String address) {
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
