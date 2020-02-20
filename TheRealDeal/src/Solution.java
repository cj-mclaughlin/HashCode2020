import java.io.IOException;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Hello Google!");
		InputParser parser = new InputParser();
        String filename = "/home/zack/Downloads/b_read_on.txt";
        parser.parseInput(filename);
        System.out.println(parser.numBooks);
        System.out.println(parser.numLibraries);
        System.out.println(parser.numDays);
		System.out.println("Ended");
	}
}
