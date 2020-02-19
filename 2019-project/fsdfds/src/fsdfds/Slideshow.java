package fsdfds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Slideshow {
	List<Photo> photos;
	Random rand = new Random();
	public Slideshow(List<Photo> p) {
		this.photos=p;
	}
	public boolean swap(int a, int b) {
		Photo aphoto = photos.get(a);
		Photo bphoto = photos.get(b);
		int ascore = aphoto.prev+ aphoto.next;
		int bscore = bphoto.prev+ bphoto.next;
		int total = ascore+bscore;
		int newA = aphoto.getScore(photos.get(b-1))+aphoto.getScore(photos.get(b+1));
		int newB = bphoto.getScore(photos.get(a-1))+bphoto.getScore(photos.get(a+1));
		int newTotal = newA+newB;
		if (newTotal>=total) {
			bphoto.prev= bphoto.getScore(photos.get(a-1));
			bphoto.next=bphoto.getScore(photos.get(a+1));
			aphoto.prev=aphoto.getScore(photos.get(b-1));
			bphoto.next=aphoto.getScore(photos.get(b+1));
			Photo tmp = new Photo();
			tmp.setEqualTo(aphoto);
			photos.get(a).setEqualTo(bphoto);
			photos.get(b).setEqualTo(tmp);
			return true;
		}
		return false;
	}
	public void hillClimb() {
		for (int i =0; i<photos.size(); i++) {
			Photo p = photos.get(i);
			if (i==0) {
				p.next=p.getScore(photos.get(i+1));
				continue;
			}
			if (i==photos.size()-1) {
				p.prev=p.getScore(photos.get(i-1));
				continue;
			}
			p.next=p.getScore(photos.get(i+1));
			p.prev=p.getScore(photos.get(i-1));
		}
		while(true) {
			int count=0;
			for (int i =0; i<100; i++) {
				if(swap(rand.nextInt(photos.size()-5)+1, rand.nextInt(photos.size()-5)+1)) {
					count++;
				}
			}
			if (count==0) {
				break;
			}
		}
		int val = 0;
		for (Photo p : photos) {
			val += p.next;
		}
		System.out.println(val);
		
	}
	public void genPairs() {
		for (Photo p : photos) {
			System.out.println(p.id);
			int maxScore = -1;
			List<Integer> maxes=null;
			for (Photo other : photos) {
				int score = p.getScore(other);
				if (score>maxScore) {
					maxes = new ArrayList<>();
					maxScore=score;
				}else if (score == maxScore) {
					maxes.add(other.id);
				}
			}
			p.pairs.addAll(maxes);
			p.maxscore=maxScore;
		}
	}
	public int randTraverse() {
		Set<Integer> prevNodes = new HashSet<>();
		Photo photo = null;
		int currentScore =0;
		
		for (int i =rand.nextInt(photos.size()/4); i<photos.size(); i++) {
			Photo p = photos.get(i);
			if (p.maxscore > 0) {
				photo =p;
				break;
			}
		}
		while (true) {
			if (photo.pairs.size()==0) {
				return currentScore;
			}else {
				currentScore += photo.maxscore;
				int choice = -1;
				int count =0;
				while (prevNodes.contains(
						photos.get(choice = rand.nextInt(photo.maxscore)).id)){
					if (count++==1000) {
						return currentScore;
					}
					
				}
				photo = photos.get(choice);
				prevNodes.add(photo.id);
			}
		}
	}
}
