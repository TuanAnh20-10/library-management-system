package com.library.models;

public class Member {
    private String id;
    private String email;
    private String name;

    public Member(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %s | Name: %s | Email: %s",
            id,
            name,
            email
        );
    }
}
