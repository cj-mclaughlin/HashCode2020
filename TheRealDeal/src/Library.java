import java.lang.reflect.Array;
import java.util.ArrayList;

public class Library {
    public int id;
    public ArrayList<Book> books;
    public int booksPerDay;
    public int signupTime;
    public boolean signedUp;

    public Library(int id, int booksPerDay, int signupTime) {
        this.books = new ArrayList<Book>();
        this.id = id;
        this.booksPerDay = booksPerDay;
        this.signupTime = signupTime;
        this.signedUp = false;
    }

    public void addBook(Book b) {
        books.add(b);
    }

}
