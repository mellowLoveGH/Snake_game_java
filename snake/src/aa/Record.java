package aa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

//this class is used for storage
//access data from txt file
//write new data to txt file
public class Record {
	
	//here the following codes are used for testing
//	public static void main(String[] args) {
//		String path = "ranks.txt";
//		String content = "hello";
//		boolean append = false;
//		writeToFile(path, content, append);
//		String str = readFromFile(path).trim();
//		int number = 5;
//		topInfo(str, number);
//	}

	//read from file
	public static String readFromFile(String path) {
		String content = "";
		try {
			FileReader reader = new FileReader(path);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			while ((line = br.readLine()) != null) {
				content = content + line + "\n";
				// System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}
	
	//write to or append file 
	public static void writeToFile(String path, String content, boolean append) {
		Writer writer = null;
		StringBuilder outputString = new StringBuilder();
		try {
			outputString.append(content + "\r\n");
			writer = new FileWriter(path, append); // true±íÊ¾×·¼Ó
			writer.write(outputString.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//after reading content from file
	//split the content into lines
	//then sort every line in ascending order
	//the method is used for listing top playing records
	//for GUI, the method will be called
	public static String[] topInfo(String str, int number) {
		String[] lines = str.trim().split("\n");
		
		String[] names = new String[lines.length];
		int[] scores = new int[lines.length];
		
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			String[] info = line.split(":");
			names[i] = info[0];
			scores[i] = Integer.parseInt(info[1].trim());
		}
		
		//sorting
		for (int i = 0; i < lines.length - 1; i++) {
			for (int j = 0; j < lines.length - 1 - i; j++) {
				if (scores[j] < scores[j + 1]) {
					int s = scores[j];
					scores[j] = scores[j + 1];
					scores[j + 1] = s;
					
					String n = names[j];
					names[j] = names[j + 1];
					names[j + 1] = n;
				}
			}
		}
		
		//
		String[] ranks = new String[lines.length];
		for (int i = 0; i < lines.length; i++) {
			ranks[i] = names[i] + "   " + scores[i];
			System.out.println(ranks[i]);
		}
		
		return ranks;
	}
}
