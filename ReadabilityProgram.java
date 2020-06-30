import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadabilityProgram {
	public static void main(String[] args) {
		File file = new File(args[0]);
		Scanner scan;
		try {
			scan = new Scanner(file);
			String sentences = "";
			while(scan.hasNext()) {
				sentences = scan.nextLine();
			}
			char[] chars = {};
			
			String[] sentence = sentences.split("[.?!]");
			int sentenceLength = sentence.length;
			
			String[] words =  sentences.split("\\s+");
			
			int charCount = 0;
			
			for(int i = 0 ; i < sentences.length(); i++) {
				if(sentences.charAt(i) == ' ' || sentences.charAt(i) == '\n' || sentences.charAt(i) == '\t') {
					
				}else {
					charCount++;
				}
			}
			
			double score = 4.71 * charCount/words.length + 0.5 * words.length/sentenceLength - 21.43;
//			System.out.printf("%.2f", score);
			
			
			System.out.println("words: " + words.length);
			System.out.println("Sentences: " + sentenceLength);
			System.out.println("Characters: " + charCount);
			System.out.println("The score is: " + score);
			
			score = Math.ceil(score);	
			if(score >= 1 && score < 2) {
				System.out.println("This text should be understood by 5-6 year olds.");
			}else if(score >=2  && score < 3) {
				System.out.println("This text should be understood by 6-7 year olds.");
			}else if(score >= 3 && score < 4) {
				System.out.println("This text should be understood by 7-9 year olds.");
			}else if(score >= 4 && score < 5) {
				System.out.println("This text should be understood by 9-10 year olds.");
			}else if(score >=5  && score < 6) {
				System.out.println("This text should be understood by 10-11 year olds.");
			}else if(score >= 6 && score < 7) {
				System.out.println("This text should be understood by 11-12 year olds.");
			}else if(score >= 7 && score < 8) {
				System.out.println("This text should be understood by 12-13 year olds.");
			}else if(score >= 8 && score < 9) {
				System.out.println("This text should be understood by 13-14 year olds.");
			}else if(score >= 9 && score < 10) {
				System.out.println("This text should be understood by 14-15 year olds.");
			}else if(score >= 10 && score < 11) {
				System.out.println("This text should be understood by 15-16 year olds.");
			}else if(score >= 11 && score < 12) {
				System.out.println("This text should be understood by 16-17 year olds.");
			}else if(score >= 12 && score < 13) {
				System.out.println("This text should be understood by 17-18 year olds.");
			}else if(score >= 13 && score < 14) {
				System.out.println("This text should be understood by 18-24 year olds.");
			}else if(score >= 14) {
				System.out.println("This text should be understood by 24 year olds.");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
