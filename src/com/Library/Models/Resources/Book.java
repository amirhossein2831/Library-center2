package com.Library.Models.Resources;

public class Book extends Resource {
    private final String publisher;
    private final String year;

    public Book(String id, String subject, String authorName, String categoryId, String libraryId, String publisher, int number, String year) {
        super(id, subject, authorName, categoryId, libraryId,number);
        this.publisher = publisher;
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }
    public String getYear() {
        return year;
    }
    @Override
    public StringBuilder search(String key) {
        if (getPublisher().toUpperCase().contains(key.toUpperCase())) {
            return new StringBuilder(this.getId());
        }
        return super.search(key);
    }
}
