import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputParser {
    public int numBooks;
    public int numLibraries;
    public int numDays;
    public Map<Integer, Book> books;
    public List<Library> libraries;

    public InputParser() {
        books = new HashMap<>();
        libraries = new ArrayList<>();
    }

    // sanity check
//    public static void main(String[] args) throws IOException {
//        InputParser parser = new InputParser();
//        String filename = "/home/connor/Downloads/a_example.txt";
//        parser.parseInput(filename);
//        System.out.println(parser.numBooks);
//        System.out.println(parser.numLibraries);
//        System.out.println(parser.numDays);
//    }


    // no safety checks here sorry team
    public void parseInput(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        // First Line of Input
        numBooks = scanner.nextInt();
        numLibraries = scanner.nextInt();
        numDays = scanner.nextInt();

        // books
        for (int i=0; i<numBooks; i++) {
            Book currentBook = new Book(i, scanner.nextInt());
            books.put(i, currentBook);
        }

        // library sections
        for (int l=0; l<numLibraries; l++) {
            int numBooksInLibrary = scanner.nextInt();
            int daysToSignup = scanner.nextInt();
            int numBooksPerDay = scanner.nextInt();
            Library currentLibrary = new Library(l, numBooksPerDay, daysToSignup);
            for (int b=0; b<numBooksInLibrary; b++) {
                int bookId = scanner.nextInt();
                currentLibrary.addBook(bookId);
            }
            libraries.add(currentLibrary);
        }
    }
}
