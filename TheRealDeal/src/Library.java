import java.util.*;

public class Library {
    public int id;
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
    }

    public int extractValue(Set<Integer> toBeScanned,Map<Integer,Integer> globalBooks, int days){
        int daysRemaining = days - signupTime;
        int maxValue = 0;
        int currentBook = 0;
        finalOrder = new ArrayList<>();
        while(daysRemaining!=0 && currentBook+booksPerDay<books.size()){
            for(int i=0;i<booksPerDay;i++){
                if(!toBeScanned.contains(books.get(currentBook+i))){
                    maxValue += globalBooks.get(books.get(currentBook+i));
                    finalOrder.add(currentBook+i);
                    daysRemaining--;
                    currentBook += booksPerDay;
                }
            }
            currentBook += booksPerDay;
        }
        finalOrderScore = maxValue;
        return maxValue;
    }
}
