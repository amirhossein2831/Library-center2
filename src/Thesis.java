public class Thesis extends Resource {
    private final String advisor;
    private final String defYear;

    public Thesis(String id, String subject, String authorName, String categoryId, String libraryId, String advisor, String defYear) {
        super(id, subject, authorName, categoryId, libraryId,1);
        this.advisor = advisor;
        this.defYear = defYear;
    }

    public String getAdvisor() {
        return advisor;
    }

    public String getDefYear() {
        return defYear;
    }
}
