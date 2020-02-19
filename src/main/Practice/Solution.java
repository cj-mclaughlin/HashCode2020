package Practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    int max_slices;
    int max_pizza_types;
    Map<Integer, Integer> num_slices_per_pizza;

    public static void main(String[] args) throws FileNotFoundException {
        Solution s = new Solution();
        s.processInput(args[0]);
        System.out.println(Integer.toString(s.max_slices) + " " +  Integer.toString(s.max_pizza_types));
    }

    public void solve() {
        // TODO implement
    }

    public void processInput(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        // Read first two parameters from line one
        for (int i=0; i<2; i++) {
            if (scanner.hasNextInt()) {
                if (i == 0) {
                    max_slices = scanner.nextInt();
                }
                else {
                    max_pizza_types = scanner.nextInt();
                }
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        // Read in rest of data
        Map<Integer, Integer> data = new HashMap<>();
        int i = 0;
        while (scanner.hasNextInt()) {
            data.put(i, scanner.nextInt());
            i++;
        }
        num_slices_per_pizza = data;
    }
}


// Ideas

// 1. Set up linear equation, brute force subtracting from num_slices until we find a solution

// 2. Backtracking search