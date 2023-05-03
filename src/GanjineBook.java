public class GanjineBook extends Resource {
    private final String publisher;
    private final String donor;
    private String year;

    public GanjineBook(String id, String subject, String authorName, String categoryId, String libraryId, String publisher, String year, String donor) {
        super(id, subject, authorName, categoryId, libraryId,1);
        this.publisher = publisher;
        this.year = year;
        this.donor = donor;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getYear() {
        return year;
    }

    public String getDonor() {
        return donor;
    }
}
