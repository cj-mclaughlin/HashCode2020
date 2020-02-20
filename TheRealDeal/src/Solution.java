import java.io.IOException;

public class Solution {
	static int b,c,d,e,f;
	public static void main(String[] args) throws IOException {
		System.out.println("Hello Google!");
		b= run ("src/b_read_on.txt");
		c= run("src/c_incunabula.txt");
		//d=run("src/d_tough_choices.txt");
		e=run("src/e_so_many_books.txt");
		f=run("src/f_libraries_of_the_world.txt");
		System.out.println("b "+b);
		System.out.println("c "+c);

		System.out.println("d "+d);

		System.out.println("e "+e);

		System.out.println("f "+f);
		int total = b+c+d+e+f;
		System.out.println("Total "+total);

		System.out.println("Ended");
	}
	public static int run(String fileappend) throws IOException {
		InputParser parser = new InputParser();
        String filename = fileappend;
        parser.parseInput(filename);
        System.out.println(parser.numBooks);
        System.out.println(parser.numLibraries);
        System.out.println(parser.numDays);
        for (Library l : parser.libraries) {
        	l.sortBooks(parser.books);
        }
        Solver solver = new Solver(parser.libraries, parser.books);
        solver.runSolution(parser.numDays);
        System.out.println("Final Score: "+solver.totalPoints);
        return solver.totalPoints;
	}
}
