import java.io.IOException;
import java.util.List;

public class Solution {
	static int a,b,c,d,e,f = 0;
	public static void main(String[] args) throws IOException {
		System.out.println("Hello Google!");

		//a= run("/home/connor/Documents/HashCode2020/TheRealDeal/src/a_example.txt");
		b= run("/home/connor/Documents/HashCode2020/TheRealDeal/src/b_read_on.txt");
		//c= run("/home/connor/Documents/HashCode2020/TheRealDeal/src/c_incunabula.txt");
		//d= run("/home/connor/Documents/HashCode2020/TheRealDeal/src/d_tough_choices.txt");
		//e= run("/home/connor/Documents/HashCode2020/TheRealDeal/src/e_so_many_books.txt");
		//f= run("/home/connor/Documents/HashCode2020/TheRealDeal/src/f_libraries_of_the_world.txt");

		//System.out.println("a "+a);
//		System.out.println("b "+b);
//		System.out.println("c "+c);
//		System.out.println("d "+d);
//		System.out.println("e "+e);
//		System.out.println("f "+f);

//		int total = b+c+d+e+f;
//		System.out.println("Total "+total);

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

        // Write output
		List<Library> usedLibraries = solver.usedLibraries;
		OutputWriter writer = new OutputWriter(usedLibraries);
		String outname = fileappend + "_solution.txt";
        writer.writeOutput(outname);

        return solver.totalPoints;
	}
}
