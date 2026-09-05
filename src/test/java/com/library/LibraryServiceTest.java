package com.library;

import com.library.exception.BookNotAvailableException;
import com.library.exception.BookNotFoundException;
import com.library.models.Book;
import com.library.models.Member;
import com.library.services.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryServiceTest {

    private LibraryService service;

    @BeforeEach
    void setUp() {
        service = new LibraryService();
    }

    @Test
    void addsAndFindsBook() {
        Book book = new Book("B01", "Clean Code", "Robert C. Martin");

        service.addBook(book);

        assertEquals(book, service.findBookById("b01"));
        assertEquals(1, service.getAllBooks().size());
    }

    @Test
    void rejectsDuplicateBookIdIgnoringCase() {
        service.addBook(new Book("B01", "Clean Code", "Robert C. Martin"));

        assertThrows(
            IllegalArgumentException.class,
            () -> service.addBook(new Book("b01", "Refactoring", "Martin Fowler"))
        );
    }

    @Test
    void storesMemberNameAndEmailInCorrectFields() {
        Member member = new Member("M01", "Alice", "alice@example.com");

        service.addMember(member);

        assertEquals("Alice", service.findMemberById("M01").getName());
        assertEquals("alice@example.com", service.findMemberById("M01").getEmail());
    }

    @Test
    void borrowsAndReturnsBook() {
        Book book = new Book("B01", "Clean Code", "Robert C. Martin");
        service.addBook(book);
        service.addMember(new Member("M01", "Alice", "alice@example.com"));

        service.borrowBook("B01", "M01");

        assertFalse(book.isAvailable());
        assertEquals(1, service.getActiveBorrowRecords().size());

        service.returnBook("B01", "M01");

        assertTrue(book.isAvailable());
        assertTrue(service.getActiveBorrowRecords().isEmpty());
    }

    @Test
    void rejectsBorrowingUnavailableBook() {
        service.addBook(new Book("B01", "Clean Code", "Robert C. Martin"));
        service.addMember(new Member("M01", "Alice", "alice@example.com"));
        service.addMember(new Member("M02", "Bob", "bob@example.com"));
        service.borrowBook("B01", "M01");

        assertThrows(
            BookNotAvailableException.class,
            () -> service.borrowBook("B01", "M02")
        );
    }

    @Test
    void rejectsBorrowingUnknownBook() {
        service.addMember(new Member("M01", "Alice", "alice@example.com"));

        assertThrows(
            BookNotFoundException.class,
            () -> service.borrowBook("missing", "M01")
        );
    }
}
