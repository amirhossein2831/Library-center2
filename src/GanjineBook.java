public class GanjineBook extends Book {
    private String donor;

    public GanjineBook(String id, String subject, String authorName, String categoryId, String libraryId, String publisher, int number, String year, String donor) {
        super(id, subject, authorName, categoryId, libraryId, publisher, number, year);
        this.donor = donor;
    }

    public String getDonor() {
        return donor;
    }
}
