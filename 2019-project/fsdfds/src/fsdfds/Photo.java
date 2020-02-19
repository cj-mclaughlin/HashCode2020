package fsdfds;

import java.util.HashSet;
import java.util.Set;

public class Photo {
	int id;
	int maxscore=0;
	boolean isHorz=true;
	Set<Integer> tags = new HashSet<>();
	Set<Integer> pairs = new HashSet<>();
	int prev=0;
	int next=0;
	
	public int getScore(Photo other) {
		int both = 0;
		int meNotOther =0;
		int otherNotMe =0;
		for (int i : tags) {
			if (other.tags.contains(i)) {
				both++;
			}else {
				meNotOther++;
			}
		}
		for (int i : other.tags) {
			if (!tags.contains(i)) {
				otherNotMe++;
			}
		}
		return Math.min(Math.min(both, meNotOther), otherNotMe);
	}
	public void setEqualTo(Photo other) {
		this.id=other.id;
		this.maxscore=other.maxscore;
		this.prev=other.prev;
		this.next=other.prev;
		tags = new HashSet<>();
		for (Integer t : other.tags) {
			tags.add(t);
		}
	}
}
