public class Resource {
    private String id;
    private String subject;
    private String authorName;
    private String number;
    private String categoryId;
    private String libraryId;

    public Resource(String id, String subject, String authorName, String number, String categoryId, String libraryId) {
        this.id = id;
        this.subject = subject;
        this.authorName = authorName;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getLibraryId() {
        return libraryId;
    }
}
