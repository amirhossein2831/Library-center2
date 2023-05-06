import java.util.Date;

public interface Action {
    public void buy(SellingBook sellingBook);

    public boolean read(GanjineBook ganjineBook, Date date);
}
