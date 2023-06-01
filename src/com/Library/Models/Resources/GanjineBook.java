package com.Library.Models.Resources;

import java.util.Date;

public class GanjineBook extends Resource {
    private final String publisher;
    private final String donor;
    private String year;
    private Date date;

    public GanjineBook(String id, String subject, String authorName, String categoryId, String libraryId, String publisher, String year, String donor) {
        super(id, subject, authorName, categoryId, libraryId,1);
        this.publisher = publisher;
        this.year = year;
        this.donor = donor;
        date = null;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public StringBuilder search(String key) {
        if (getPublisher().toUpperCase().contains(key.toUpperCase())) {
            return new StringBuilder(this.getId());
        }
        return super.search(key);
    }
}
