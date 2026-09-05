package com.library.models;

public class Book {
    private String id;
    private String title;
    private String author;
    private Boolean available;

    public Book (String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getId() {
        return id;
    }

     public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

     public void borrowBook() {
        this.available = false;
    }

    public void returnBook() {
        this.available = true;
    }

    @Override 
    public String toString() {
         return String.format(
            "ID: %s | Title: %s | Author: %s | Status: %s",
            id,
            title,
            author,
            available ? "Available" : "Borrowed"
        );
    }
}
