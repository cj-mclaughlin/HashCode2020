import java.util.*;

public class Library {
    public int id=0;
    public List<Integer> books;
    public List<Integer> finalOrder;
    public Integer finalOrderScore; //this is fucking ugly
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

    public void sortBooks(Map<Integer,Integer> globalBooks){
        Collections.sort(books,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return globalBooks.get(o1).compareTo(globalBooks.get(o2));
            }
        });
        Collections.reverse(books);
    }

    public int extractValue(Set<Integer> toBeScanned,Map<Integer,Integer> globalBooks, int days){
        long daysRemaining = days - signupTime;
        daysRemaining = Math.max(daysRemaining, 0);

        int maxValue = 0;
        int currentBook = 0;
        finalOrder = new ArrayList<>();
        while(daysRemaining!=0 && currentBook<books.size()){
                if(!toBeScanned.contains(books.get(currentBook))){
                    maxValue += globalBooks.get(books.get(currentBook));
                    finalOrder.add(books.get(currentBook));
                    daysRemaining--;
                }
            currentBook++;
        }
        finalOrderScore = maxValue;
        return maxValue;
    }
}
