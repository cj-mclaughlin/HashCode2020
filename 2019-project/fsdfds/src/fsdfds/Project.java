package fsdfds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {
	public static void log(String s) {
		System.out.println(s);
	}
	public static void main(String[] args) throws IOException{
		log("Started");
		File f = new File("/home/zack/Downloads/qualification_round_2019.in/d_pet_pictures.txt");
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = reader.readLine(); // ignore first line;
		log(line);
		Map<String,Integer> tags = new HashMap<>();
		List<Photo> photos = new ArrayList<>();
		int idcounter=0;
		while((line=reader.readLine())!=null) {
			String[] linep = line.split(" ");
			Photo photo = new Photo();
			for (int i = 2; i<linep.length; i++) {
				if (tags.containsKey(linep[i])){
					photo.tags.add(
							tags.get(linep[i]));
				}else {
					tags.put(linep[i], tags.keySet().size());
					i--;
				}
			}
			photo.id=idcounter++;
			photos.add(photo);
		}
		
		Slideshow slideshow = new Slideshow(photos);
		//slideshow.genPairs();
		/*for (Photo p : photos) {
			log("Photo "+p.id);
			for (Integer i : p.tags) {
				log("  "+i);
			}
			log("  Pairs");
			for (int j : p.pairs) {
				log("    Photo: "+j);
			}
		}*/
		slideshow.hillClimb();
		log("done");
		/*int max =0; 
		for (int i =0; i<100000000; i++) {
			int score = slideshow.randTraverse();
			if (score>max) {
				max=score;
				log(" "+max);
			}
		}
		log("max: "+max);*/
		log("Ended");
		
	}
}