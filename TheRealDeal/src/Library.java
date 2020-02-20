import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

    public int extractValue(Set<Integer> toBeScanned,HashMap<Integer,Integer> globalBooks, int days){
        int daysRemaining = days - signupTime;
        int maxValue = 0;
        int currentBook = 0;
        //implement sorting rn
        while(daysRemaining!=0 && currentBook<books.size()){
            if(!toBeScanned.contains(books.get(currentBook))){
                maxValue += globalBooks.get(books.get(currentBook));
                daysRemaining--;
                currentBook++;
            }
            currentBook++;
        }
        return maxValue;
    }
}
