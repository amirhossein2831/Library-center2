public class Borrow {
    private String userId;
    private String resourceId;
    private String libraryId;
    private String userType;
    private String resourceType;

    public Borrow(String userId, String resourceId, String libraryId, String userType, String resourceType) {
        this.userId = userId;
        this.resourceId = resourceId;
        this.libraryId = libraryId;
        this.userType = userType;
        this.resourceType = resourceType;
    }

    public String getUserId() { 
        return userId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public String getUserType() {
        return userType;
    }

    public String getResourceType() {
        return resourceType;
    }
}
