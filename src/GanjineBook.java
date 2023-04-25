public class GanjineBook extends Resource {
    private String publisher;
    private String donor;

    public GanjineBook(String id, String subject, String authorName, String categoryId, String libraryId, String publisher, String donor) {
        super(id, subject, authorName, categoryId, libraryId);
        this.publisher = publisher;
        this.donor = donor;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDonor() {
        return donor;
    }
}
