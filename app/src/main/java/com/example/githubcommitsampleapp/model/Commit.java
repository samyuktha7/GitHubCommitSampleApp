package com.example.githubcommitsampleapp.model;

public class Commit {

    private Author author;
    private String message;

    public Commit(Author author, String message) {
        this.author = author;
        this.message = message;
    }

    public Author getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }
}
