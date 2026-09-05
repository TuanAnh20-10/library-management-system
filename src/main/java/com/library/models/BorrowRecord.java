package com.library.models;

import java.time.LocalDate;

public class BorrowRecord {
    private String bookId;
    private String memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowRecord(String bookId, String memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
    }

    public String getBookId() {
        return bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public void markReturned() {
        this.returnDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return String.format(
            "Book ID: %s | Member ID: %s | Borrowed: %s | Returned: %s",
            bookId,
            memberId,
            borrowDate,
            returnDate == null ? "Not yet" : returnDate
        );
    } 
}
