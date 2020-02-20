import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OutputWriter {
    List<Library> libraries; // list of libraries we are signing up, they should have list of integer books

    public OutputWriter(List<Library> libraries) {
        this.libraries = libraries;
    }

    // sanity check
//    public static void main(String[] args) throws IOException {
//        List<Library> libs = new ArrayList<>();
//        libs.add(new Library(1, 0, 0));
//        libs.add(new Library(5, 0, 0));
//        libs.add(new Library(8, 0, 0));
//        OutputWriter test = new OutputWriter(libs.size(), libs);
//        test.writeOutput("InitialTest.txt");
//    }

    public void writeOutput(String filename) throws IOException {
        File file = new File(filename);
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);

        // write initial lines
        String firstLine = String.valueOf(libraries.size()) + "\n";
        br.write(firstLine);

        // write library sections
        for (Library lib : libraries) {
            br.write(libraryOutput(lib));
        }
        br.close();
        fr.close();
    }

    public String libraryOutput(Library library) {
        int id = library.id;

        List<Integer> booksScanned = library.finalOrder; // TODO replace when method implemented in library

//        // example list
//        List<Integer> booksScanned = new ArrayList<>();
//        booksScanned.add(1);
//        booksScanned.add(2);
//        booksScanned.add(3);

        int numScanned = booksScanned.size();

        String line1 = String.valueOf(id) + " " + String.valueOf(numScanned);

        // list of books to string
        StringBuilder builder  = new StringBuilder();
        Iterator<Integer> iter = booksScanned.iterator();
        while(iter.hasNext())
        {
            builder.append(iter.next());
            if(iter.hasNext()){
                builder.append(" ");
            }
        }
        String line2 = builder.toString();

        String outputString = line1 + "\n" + line2 + "\n";
        return outputString;
    }
}

// Outline

// first line - # of libraries to sign up

// then for each library:
// first line - id of library, # of books to scan from library
// second line - list of books scanned from that library