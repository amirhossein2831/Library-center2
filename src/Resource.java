import java.util.ArrayList;

public abstract class Resource {
    private final String id;
    private final String subject;
    private final String authorName;
    private final String categoryId;
    private final String libraryId;
    private int number;
    private ArrayList<String> comment;

    public Resource(String id, String subject, String authorName, String categoryId, String libraryId,int number) {
        this.id = id;
        this.subject = subject;
        this.authorName = authorName;
        this.categoryId = categoryId;
        this.libraryId = libraryId;
        this.number = number;
        comment = new ArrayList<>();
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

    public int getNumber() {
        return number;
    }

    public void decreaseNumber() {
        number--;
    }

    public void addComment(String comment) {
        this.comment.add(comment);
    }

    public StringBuilder search(String key) {
        if (subject.toUpperCase().contains(key.toUpperCase())) {
            return new StringBuilder(this.getId());
        } else if (authorName.toUpperCase().contains(key.toUpperCase())) {
            return new StringBuilder(this.getId());
        }
        return new StringBuilder();
    }
}
