public class Thesis extends Resource {
    private String advisor;
    private String defYear;

    public Thesis(String id, String subject, String authorName, String categoryId, String libraryId, String advisor, String defYear) {
        super(id, subject, authorName, categoryId, libraryId);
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
