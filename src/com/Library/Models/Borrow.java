package com.Library.Models;

import com.Library.Models.Resources.Book;
import com.Library.Models.Resources.Resource;
import com.Library.Models.Resources.Thesis;
import com.Library.Models.Users.Professor;
import com.Library.Models.Users.Staff;
import com.Library.Models.Users.Student;
import com.Library.Models.Users.User;

import java.util.Date;
import java.util.HashMap;

public class Borrow {
    private String userId;
    private String resourceId;
    private String libraryId;
    private String userType;
    private String resourceType;
    private Date date;

    public Borrow(String userId, String resourceId, String libraryId,Date date) {
        this.userId = userId;
        this.resourceId = resourceId;
        this.libraryId = libraryId;
        this.date = date;
    }

    public void evaluateUserType(HashMap<String, User> users) {
        User user = users.get(getUserId());

        if (user instanceof Staff) {
            setUserType("staff");
        } else if (user instanceof Professor) {
            setUserType("professor");
        } else if (user instanceof Student) {
            setUserType("student");
        }
    }
    public void evaluateResourceType(HashMap<String, Resource> resources) {
        Resource resource = resources.get(getUserId());
        if (resource instanceof Book) {
            setResourceType("book");
        } else if (resource instanceof Thesis) {
            setResourceType("thesis");
        }
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

    public Date getDate() {
        return date;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
