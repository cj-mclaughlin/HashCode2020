package Practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    int max_slices;
    int max_pizza_types;
    Map<Integer, Integer> num_slices_per_pizza;
    int result;

    public static void main(String[] args) throws FileNotFoundException {
        Solution s = new Solution();
        s.processInput(args[0]);
        System.out.println(Integer.toString(s.solve()));
    }


    public int solve() {
        int [] domain = {-1,0,1};
        HashMap<Integer, Integer> assignments = new HashMap<>();
        for (int i=0; i<max_pizza_types; i++) {
            assignments.put(i, domain[0]); // set all variables to unassigned
        }
        HashMap<Integer, Integer> solution = backtrack(assignments);
        return mapSum(backtrack(solution));
    }

    private HashMap<Integer, Integer> backtrack(HashMap<Integer, Integer> assignments) {
        if (assignments == null) {
            System.out.println("Yikes");
            return null;
        }
        if (mapSum(assignments) == max_slices) {
            System.out.println("Found Solution");
            return assignments;
        }
        for (int currentPizzaIdx=max_pizza_types-1; currentPizzaIdx>0; currentPizzaIdx--) { // start at pizzas with most slices
            if (assignments.get(currentPizzaIdx) == -1) { // select unassigned variable
                for (int domainVal = 1; domainVal >= 0; domainVal--) { // start by trying to add the pizza to assignment
                    if (domainVal == 1) { // if we are trying to add the pizza, check preemptive failure
                        int currentSum = mapSum(assignments);
                        if (currentSum + num_slices_per_pizza.get(currentPizzaIdx) > max_slices) {
                            continue; // couldn't have worked anyways
                        }
                    }
                    HashMap<Integer, Integer> trial = deepCopy(assignments);
                    trial.put(currentPizzaIdx, domainVal);
                    HashMap<Integer, Integer> recursiveResult = backtrack(trial);
                    if (recursiveResult != null && mapSum(recursiveResult) <= max_slices) {
                        result = mapSum(recursiveResult);
                        return recursiveResult;
                    }
                    else if (recursiveResult == null) {
                        assignments.put(currentPizzaIdx, -1); // remove assignment on failure
                    }
                }
            }
        }
        return null;
    }

    private ArrayList<Integer> readMap(Map<Integer, Integer> map) {
        // TODO read into expected output format
        return null;
    }

    private HashMap<Integer, Integer> deepCopy(Map<Integer, Integer> original) {
        HashMap<Integer, Integer> copy = new HashMap<>();
        for (Integer key : original.keySet()) {
            copy.put(key, original.get(key));
        }
        return copy;
    }

    // running sum of slices from current assignment
    public int mapSum(Map<Integer, Integer> map) {
        int sum = 0;
        if (map == null) {
            return sum;
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                sum += num_slices_per_pizza.get(key);
            }
        }
        return sum;
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