public class Resource {
    private final String id;
    private final String subject;
    private final String authorName;
    private final String categoryId;
    private final String libraryId;

    public Resource(String id, String subject, String authorName, String categoryId, String libraryId) {
        this.id = id;
        this.subject = subject;
        this.authorName = authorName;
        this.categoryId = categoryId;
        this.libraryId = libraryId;
    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getLibraryId() {
        return libraryId;
    }
}
