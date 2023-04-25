public class Book extends Resource {
    private final String publisher;
    private final int number;
    private final String year;

    public Book(String id, String subject, String authorName, String categoryId, String libraryId, String publisher, int number, String year) {
        super(id, subject, authorName, categoryId, libraryId);
        this.publisher = publisher;
        this.number = number;
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getNumber() {
        return number;
    }

    public String getYear() {
        return year;
    }
}
