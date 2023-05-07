import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Professor extends User implements Buy, Read {
    private ArrayList<SellingBook> buys;
    private GanjineBook read;
    public Professor(String id, String pass, String firstName, String lastName, String nationalId, String year, String address) {
        super(id, pass, firstName, lastName, nationalId, year, address);
        buys = new ArrayList<>();
        read = null;
    }
    public void buy(SellingBook sellingBook) {
        buys.add(sellingBook);
    }

    @Override
    public boolean read(GanjineBook ganjineBook, Date date) {
        if (IsGanjinehAvail(ganjineBook, date)) {
            read = ganjineBook;
            read.setDate(date);
            return true;
        }
        return false;
    }
    private boolean IsGanjinehAvail(GanjineBook ganjineBook, Date date) {
        Date dateHold = ganjineBook.getDate();
        if (dateHold == null) {
            return true;
        }
        long firstTime = dateHold.getTime() / 3600000;
        long secondTime = date.getTime() / 3600000;
        long periodTime = secondTime - firstTime;
        return periodTime >= 2;
    }
    @Override
    public void addComment(String comment,Resource resource) {
        resource.addComment(comment);
    }
}
