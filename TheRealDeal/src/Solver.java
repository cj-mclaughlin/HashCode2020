import java.util.List;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solver {

	Set<Integer> toBeScanned = new HashSet<>();
	Map<Integer, Integer> globalVals;
	List<Library> libraries;
	public Solver(List<Library> libs, Map<Integer, Integer> books) {
		this.globalVals=books;
		this.libraries=libs;
	}
	
	public void runSolution(int totalDays) {
		for (int day=0; day<totalDays; ) {
			Library l = findMaxScoringLibrary(totalDays-day);
			toBeScanned.addAll(l.start());
			days+=l.signupTime;
		}
	}
	public Library findMaxScoringLibrary(int daysLeft) {
		int maxScore = 0;
		Library maxLibrary = null;
		for (Library l : libraries) {
			int val = l.extractValue(toBeScanned, globalVals, daysLeft);
			// TODO ADD PRIORITIZATION FOR LIBRARIES THAT WILL TAKE A LONG TIME;
			if (val>maxScore) {
				maxScore=val;
				maxLibrary = l;
			}
		}
		return maxLibrary;
	}
}
