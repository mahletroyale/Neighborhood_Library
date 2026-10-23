
import java.util.Scanner;

public class Library {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        Book[] books = new Book[20];

        books[0] = new Book(1, "9780061120084", "It Ends with Us by Colleen Hoover");
        books[1] = new Book(2, "9780451524935", "It Starts with Us by Colleen Hoover");
        books[2] = new Book(3, "9780743273565", "November 9 by Colleen Hoover");
        books[3] = new Book(4, "9780316769488", "Reminders of Him by Colleen Hoover");
        books[4] = new Book(5, "9780141439518", "Othello by William Shakespeare");
        books[5] = new Book(6, "9780547928227", "To kill a Mockingbird by Harper Lee");
        books[6] = new Book(7, "9780439708180", "Harry Potter by J.K. Rowling");
        books[7] = new Book(8, "9780062315007", "The Alchemist by Paulo Coelho");
        books[8] = new Book(9, "9780143127550", "Attached by Amir Levine and Rachel Heller");
        books[9] = new Book(10, "9780735211292", "The Hate You Give by Angie Thomas");
        books[10] = new Book(11, "9780062457714", "The Subtle Art by Mark Manson");
        books[11] = new Book(12, "9781501124020", "The Heart of a Woman by Maya Angelou");
        books[12] = new Book(13, "9781501110368", "The Hunger Games by Suzanne Collins");
        books[13] = new Book(14, "9780735219090", "And Still I Rise by Maya Angelou");
        books[14] = new Book(15, "9780525559474", "Educated by Tara Westover");
        books[15] = new Book(16, "9780385490818", "The Handmaid's Tale by Margaret Atwood");
        books[16] = new Book(17, "9780307277671", "The Road by Cormac McCarthy");
        books[17] = new Book(18, "9780142437230", "The Fault in Our Stars by John Green");
        books[18] = new Book(19, "9780140449136", "The Odyssey by Homer");
        books[19] = new Book(20, "9780140449181", "The Republic by Plato");


        System.out.println("==================================================");
        System.out.println("     WELCOME TO AYELE'S LEGACY LIBRARY");
        System.out.println("==================================================");
        System.out.println();

        System.out.print("Hi there\uD83D\uDC4B\uD83C\uDFFD what's your name? ");
        String userName = scanner.nextLine().trim();

        while (userName.isEmpty()) {
            System.out.print("Please enter your name: ");
            userName = scanner.nextLine().trim();
        }

        System.out.println();
        System.out.println("Nice to meet you, " + userName + "!");
        System.out.println("Let's find your next great read!");

        boolean running = true;


        while (running) {


            System.out.println("Would you like to...");
            System.out.println();

            System.out.println("1. Browse Available Books");
            System.out.println("2. View Checked Out Books");
            System.out.println("3. Exit Library");

            System.out.print("Your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    showAvailableBooks(books, userName);
                    break;

                case "2":
                    showCheckedOutBooks(books);
                    break;

                case "3":
                    System.out.println();
                    System.out.println("Thanks for visiting, " + userName + "!");
                    System.out.println("Happy reading and see you next time!");
                    running = false;
                    break;

                default:
                    System.out.println("Oops! That's not a valid option.");
                    System.out.println("Please choose 1, 2, or 3.");
            }
        }

    }


    public static void showAvailableBooks(Book[] books, String userName) {

        System.out.println();
        System.out.println("===== AVAILABLE BOOKS =====");

        boolean found = false;

        for (Book book : books) {

            if (!book.isCheckedOut()) {

                System.out.println(
                        "ID: " + book.getId() +
                                " | ISBN: " + book.getIsbn() +
                                " | Title: " + book.getTitle()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("Sorry! All books are checked out.");
            return;
        }

        System.out.println();
        System.out.println("Found something interesting, " + userName + "?");
        System.out.print("Enter a book ID to check out (0 to go back): ");

        int id = readId();

        if (id == 0) {
            return;
        }

        for (Book book : books) {

            if (book.getId() == id && !book.isCheckedOut()) {

                System.out.println();
                System.out.println("Great choice, " + userName + "!");
                System.out.println("You've selected: " + book.getTitle());

                System.out.print("Would you like to check out this book? (Y/N): ");
                String answer = scanner.nextLine();

                if (answer.equalsIgnoreCase("Y")) {

                    book.checkOut(userName);

                    System.out.println();
                    System.out.println("Success! You've checked out: " + book.getTitle());
                    System.out.println("Enjoy your new read, " + userName + "!");

                } else {
                    System.out.println("No problem! Returning to the home screen.");
                }

                return;
            }
        }

        System.out.println("Sorry! Book not found or already checked out.");
    }

    public static void showCheckedOutBooks(Book[] books) {

        System.out.println();
        System.out.println("===== CHECKED OUT BOOKS =====");

        boolean found = false;

        for (Book book : books) {

            if (book.isCheckedOut()) {

                System.out.println(
                        "ID: " + book.getId() +
                                " | ISBN: " + book.getIsbn() +
                                " | Title: " + book.getTitle() +
                                " | Checked out to: " + book.getCheckedOutTo()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No books are currently checked out.");
        }

        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("C - Check In a Book");
        System.out.println("X - Return to Home Screen");

        System.out.print("Your choice: ");

        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("C")) {

            checkInBook(books);

        } else if (choice.equalsIgnoreCase("X")) {

            System.out.println("Returning to the home screen...");

        } else {

            System.out.println("Invalid option. Returning to home screen.");
        }
    }


    public static void checkInBook(Book[] books) {

        System.out.println();
        System.out.println("===== BOOK CHECK IN =====");

        System.out.print("Which book would you like to return? Enter its ID: ");

        int id = readId();

        for (Book book : books) {

            if (book.getId() == id && book.isCheckedOut()) {

                System.out.println();
                System.out.println("You're returning: " + book.getTitle());

                book.checkIn();

                System.out.println("Book returned successfully!");
                System.out.println("We hope you enjoyed reading it!");
                System.out.println("Returning to the home screen...");

                return;
            }
        }

        System.out.println("Book not found or not currently checked out.");
    }


    public static int readId() {

        while (true) {

            try {

                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.print("Please enter a valid number: ");
            }
        }
    }
}


