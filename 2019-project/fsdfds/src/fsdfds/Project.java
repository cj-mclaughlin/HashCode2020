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
	public static void runProg() throws IOException{
		log("Started");
		File f = new File("/home/zack/Downloads/qualification_round_2019.in/b_lovely_landscapes.txt");
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = reader.readLine(); // ignore first line;
		log(line);
		Map<String,Integer> tags = new HashMap<>();
		List<Photo> photos = new ArrayList<>();
		int idcounter=0;
		while((line=reader.readLine())!=null) {
			String[] linep = line.split(" ");
			Photo photo = new Photo();
			if (linep[0].equals("H")) {
				photo.isHorz=true;
			}else {
				photo.isHorz=false;
			}
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
		reader.close();
		Slideshow slideshow = new Slideshow(photos);
		slideshow.hillClimb();
		
		log("done");
		log("Ended");
	}
	public static void main(String[] args) throws IOException{
		for (int i=0; i<8; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						runProg();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
		
		
	}
}