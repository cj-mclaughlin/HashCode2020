import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class Library {
    public int id;
    public ArrayList<Integer> books;
    public int booksPerDay;
    public int signupTime;
    public boolean signedUp;

    public Library(int id, int booksPerDay, int signupTime) {
        this.books = new ArrayList<>();
        this.id = id;
        this.booksPerDay = booksPerDay;
        this.signupTime = signupTime;
        this.signedUp = false;
    }

    public void addBook(int bookId) {
        books.add(bookId);
    }

}
