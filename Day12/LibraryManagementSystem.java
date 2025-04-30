
import java.util.Scanner;

class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next;
    Book prev;

    Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    private Book head = null;
    private Book tail = null;

    // Add at beginning
    public void addAtBeginning(String title, String author, String genre, int id, boolean available) {
        Book newBook = new Book(title, author, genre, id, available);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        System.out.println("Book added at the beginning.");
    }

    // Add at end
    public void addAtEnd(String title, String author, String genre, int id, boolean available) {
        Book newBook = new Book(title, author, genre, id, available);
        if (head == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        System.out.println("Book added at the end.");
    }

    // Add at specific position
    public void addAtPosition(int pos, String title, String author, String genre, int id, boolean available) {
        if (pos <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos == 1) {
            addAtBeginning(title, author, genre, id, available);
            return;
        }

        Book newBook = new Book(title, author, genre, id, available);
        Book temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null || temp.next == null) {
            addAtEnd(title, author, genre, id, available);
        } else {
            newBook.next = temp.next;
            newBook.prev = temp;
            temp.next.prev = newBook;
            temp.next = newBook;
            System.out.println("Book added at position " + pos);
        }
    }

    // Remove by Book ID
    public void removeById(int bookId) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                if (temp == head && temp == tail) {
                    head = tail = null;
                } else if (temp == head) {
                    head = head.next;
                    head.prev = null;
                } else if (temp == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                System.out.println("Book with ID " + bookId + " removed.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID not found.");
    }

    // Search by title or author
    public void search(String keyword) {
        boolean found = false;
        Book temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(keyword) || temp.author.equalsIgnoreCase(keyword)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Book not found.");
    }

    // Update availability
    public void updateAvailability(int bookId, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = status;
                System.out.println("Availability updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID not found.");
    }

    // Display books forward
    public void displayForward() {
        Book temp = head;
        System.out.println("\n--- Books (Forward Order) ---");
        while (temp != null) {
            printBook(temp);
            temp = temp.next;
        }
    }

    // Display books in reverse
    public void displayReverse() {
        Book temp = tail;
        System.out.println("\n--- Books (Reverse Order) ---");
        while (temp != null) {
            printBook(temp);
            temp = temp.prev;
        }
    }

    // Count books
    public void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total number of books: " + count);
    }

    private void printBook(Book b) {
        System.out.printf("ID: %d | Title: %s | Author: %s | Genre: %s | Available: %s\n",
                b.bookId, b.title, b.author, b.genre, b.isAvailable ? "Yes" : "No");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int choice;

        do {
            System.out.println("\n--- Library Management ---");
            System.out.println("1. Add Book (Beginning)");
            System.out.println("2. Add Book (End)");
            System.out.println("3. Add Book (Position)");
            System.out.println("4. Remove Book by ID");
            System.out.println("5. Search Book by Title/Author");
            System.out.println("6. Update Availability Status");
            System.out.println("7. Display All Books (Forward)");
            System.out.println("8. Display All Books (Reverse)");
            System.out.println("9. Count Total Books");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1, 2, 3 -> {
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Book ID: ");
                    int id = sc.nextInt();
                    System.out.print("Available? (true/false): ");
                    boolean status = sc.nextBoolean();
                    if (choice == 1)
                        library.addAtBeginning(title, author, genre, id, status);
                    else if (choice == 2)
                        library.addAtEnd(title, author, genre, id, status);
                    else {
                        System.out.print("Position: ");
                        int pos = sc.nextInt();
                        library.addAtPosition(pos, title, author, genre, id, status);
                    }
                }
                case 4 -> {
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    library.removeById(id);
                }
                case 5 -> {
                    System.out.print("Enter Title or Author: ");
                    String keyword = sc.nextLine();
                    library.search(keyword);
                }
                case 6 -> {
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    System.out.print("New Status (true/false): ");
                    boolean status = sc.nextBoolean();
                    library.updateAvailability(id, status);
                }
                case 7 -> library.displayForward();
                case 8 -> library.displayReverse();
                case 9 -> library.countBooks();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
}

