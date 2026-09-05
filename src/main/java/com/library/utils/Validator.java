package com.library.utils;

public class Validator {
     public static void validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "ID cannot be empty."
            );
        }
    }

    public static void validateName(String name) {
        if (name == null || name.trim().length() < 2) {
            throw new IllegalArgumentException(
                "Name must contain at least 2 characters."
            );
        }
    }

    public static void validateEmail(String email) {
        if (
            email == null ||
            !email.contains("@")
        ) {
            throw new IllegalArgumentException(
                "Invalid email."
            );
        }
    }
}
