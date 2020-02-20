import java.util.*;

public class Solver {

        Set<Integer> toBeScanned = new HashSet<>();
        Map<Integer, Integer> globalVals;
        List<Library> libraries;
        public List<Library> usedLibraries = new ArrayList<>();

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

                        usedLibraries.add(l);
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
                        int val = l.extractValue(toBeScanned, globalVals, daysLeft)-l.signupTime*l.signupTime*l.signupTime
                        		+ 100/l.booksPerDay ;
                        if (val>maxScore) {
                                maxScore=val;
                                maxLibrary = l;
                        }
                }
                return maxLibrary;
        }
}
