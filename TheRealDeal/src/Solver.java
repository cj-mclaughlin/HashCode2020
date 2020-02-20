import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solver {

        Set<Integer> toBeScanned = new HashSet<>();
        Map<Integer, Integer> globalVals;
        List<Library> libraries;
        int totalPoints=0;
        public Solver(List<Library> libs, Map<Integer, Integer> books) {
                this.globalVals=books;
                this.libraries=libs;
                Collections.sort(libraries,new Comparator<Library>(){
            @Override
            public int compare(Library o1, Library o2) {
                return o1.signupTime - o2.signupTime;
            }
        });
        }

        public void runSolution(int totalDays) {
                for (int day=0; day<totalDays; ) {
                        Library l = findMaxScoringLibrary(totalDays-day);
                        System.out.println("Day "+day+" doing library "+l.id);
                        System.out.println("NumBooks: "+l.books.size());
                        System.out.println("SignupTime: "+l.signupTime);
                        System.out.println("LibraryScore: "+l.finalOrderScore);
                        System.out.println("BooksPerDay: "+l.booksPerDay);
                        toBeScanned.addAll(l.finalOrder);
                        totalPoints+=l.finalOrderScore;
                        day+=l.signupTime;
                        libraries.remove(l);

                }
        }
        public Library findMaxScoringLibrary(int daysLeft) {
                int maxScore = Integer.MIN_VALUE;
                Library maxLibrary = null;

                for (Library l : libraries) {
                        int val = l.extractValue(toBeScanned, globalVals, daysLeft)-l.signupTime*5;
                        if (val>maxScore) {
                                maxScore=val;
                                maxLibrary = l;
                        }
                }
                return maxLibrary;
        }
}
