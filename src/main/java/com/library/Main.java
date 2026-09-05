package com.library;

import com.library.models.Book;
import com.library.models.BorrowRecord;
import com.library.models.Member;
import com.library.services.LibraryService;
import com.library.utils.Validator;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final LibraryService service = new LibraryService();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        addBook();
                        break;
                    case "2":
                        viewBooks();
                        break;
                    case "3":
                        searchBook();
                        break;
                    case "4":
                        addMember();
                        break;
                    case "5":
                        viewMembers();
                        break;
                    case "6":
                        borrowBook();
                        break;
                    case "7":
                        returnBook();
                        break;
                    case "8":
                        viewBorrowedBooks();
                        break;
                    case "0":
                        System.out.println("Goodbye.");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (RuntimeException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n========== LIBRARY MANAGEMENT ==========");
        System.out.println("1. Add Book");
        System.out.println("2. View Books");
        System.out.println("3. Search Book by Title");
        System.out.println("4. Add Member");
        System.out.println("5. View Members");
        System.out.println("6. Borrow Book");
        System.out.println("7. Return Book");
        System.out.println("8. View Borrowed Books");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addBook() {
        System.out.print("Book ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Author: ");
        String author = scanner.nextLine().trim();

        Validator.validateId(id);
        Validator.validateName(title);
        Validator.validateName(author);

        service.addBook(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    private static void viewBooks() {
        List<Book> books = service.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void searchBook() {
        System.out.print("Keyword: ");
        String keyword = scanner.nextLine();
        List<Book> books = service.searchBooksByTitle(keyword);

        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void addMember() {
        System.out.print("Member ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        Validator.validateId(id);
        Validator.validateName(name);
        Validator.validateEmail(email);

        service.addMember(new Member(id, name, email));
        System.out.println("Member added successfully.");
    }

    private static void viewMembers() {
        List<Member> members = service.getAllMembers();

        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }

        for (Member member : members) {
            System.out.println(member);
        }
    }

    private static void borrowBook() {
        System.out.print("Book ID: ");
        String bookId = scanner.nextLine().trim();

        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();

        service.borrowBook(bookId, memberId);
        System.out.println("Book borrowed successfully.");
    }

    private static void returnBook() {
        System.out.print("Book ID: ");
        String bookId = scanner.nextLine().trim();

        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();

        service.returnBook(bookId, memberId);
        System.out.println("Book returned successfully.");
    }

    private static void viewBorrowedBooks() {
        List<BorrowRecord> records = service.getActiveBorrowRecords();

        if (records.isEmpty()) {
            System.out.println("No books are currently borrowed.");
            return;
        }

        for (BorrowRecord record : records) {
            System.out.println(record);
        }
    }
}
