public class SellingBook extends Book {
    private final String price;
    private final String discount;

    public SellingBook(String id, String subject, String authorName, String categoryId, String libraryId, String publisher, int number, String year, String price, String discount) {
        super(id, subject, authorName, categoryId, libraryId, publisher, number, year);
        this.price = price;
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }
}
