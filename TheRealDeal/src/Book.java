public class Book {
    public int id;
    public int score;
    public boolean scanned;

    public Book(int id, int score) {
        this.id = id;
        this.score = score;
        this.scanned = false;
    }

    public void scan() {
        scanned = true;
    }
}
