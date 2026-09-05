package com.library.services;


import java.util.ArrayList;
import java.util.List;

import com.library.exception.BookNotAvailableException;
import com.library.exception.BookNotFoundException;
import com.library.models.Book;
import com.library.models.BorrowRecord;
import com.library.models.Member;
public class LibraryService {
    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<BorrowRecord> borrowRecords = new ArrayList<>();

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
    public  Book findBookById(String id) {
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(id)) {
                return book;
            }
        }

        return null;
    }

    public void addBook (Book book){
        if(findBookById(book.getId()) != null) {
            throw new IllegalArgumentException(
                "Book ID already exists."
            );
        }

        books.add(book);
    }

    public List<Book> searchBooksByTitle(String keyword) {
        List<Book> results = new ArrayList<>();

        for (Book book : books) {
            if (
                book.getTitle()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())
            ) {
                results.add(book);
            }
        }

        return results;
    }

    public void addMember(Member member) {
        if (findMemberById(member.getId()) != null) {
            throw new IllegalArgumentException(
                "Member ID already exists."
            );
        }

        members.add(member);
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    public Member findMemberById(String id) {
        for (Member member : members) {
            if (member.getId().equalsIgnoreCase(id)) {
                return member;
            }
        }

        return null;
    }

    public void borrowBook(String bookId, String memberId) {
        Book book = findBookById(bookId);

        if (book == null) {
            throw new BookNotFoundException(
                "Book not found."
            );
        }

        Member member = findMemberById(memberId);

        if (member == null) {
            throw new IllegalArgumentException(
                "Member not found."
            );
        }

        if (!book.isAvailable()) {
            throw new BookNotAvailableException(
                "Book is currently borrowed."
            );
        }

        book.borrowBook();

        BorrowRecord record = new BorrowRecord(
            bookId,
            memberId
        );

        borrowRecords.add(record);
    }

    public void returnBook(String bookId, String memberId) {
        Book book = findBookById(bookId);

        if (book == null) {
            throw new BookNotFoundException(
                "Book not found."
            );
        }

        BorrowRecord activeRecord = null;

        for (BorrowRecord record : borrowRecords) {
            if (
                record.getBookId().equalsIgnoreCase(bookId) &&
                record.getMemberId().equalsIgnoreCase(memberId) &&
                !record.isReturned()
            ) {
                activeRecord = record;
                break;
            }
        }

        if (activeRecord == null) {
            throw new IllegalArgumentException(
                "No active borrow record found."
            );
        }

        activeRecord.markReturned();
        book.returnBook();
    }

    public List<BorrowRecord> getActiveBorrowRecords() {
        List<BorrowRecord> activeRecords = new ArrayList<>();

        for (BorrowRecord record : borrowRecords) {
            if (!record.isReturned()) {
                activeRecords.add(record);
            }
        }

        return activeRecords;
    }
}
