import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {

	static int syllabuls = 0;
	static int poly = 0;
	static int sentenceLength = 0;
	static int wordLength = 0;
	static int charCount = 0;
	static int averageScore = 0;
	static int averageCall = 0;
	public static void main(String[] args) {

		File file = new File(args[0]);
		Scanner scan;
		String sentences = "";
		try {
			scan = new Scanner(file);
			while (scan.hasNext()) {
				sentences = scan.nextLine();
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		char[] chars = {};

		String[] sentence = sentences.split("[.?!]");
		sentenceLength = sentence.length;

		String[] words = sentences.split("\\s+");

		// calculate number of characters in words
		for (int i = 0; i < sentences.length(); i++) {
			if (sentences.charAt(i) == ' ' || sentences.charAt(i) == '\n' || sentences.charAt(i) == '\t') {

			} else {
				charCount++;
			}
		}
		
		
		for(int i = 0; i < words.length;i++) {
			syllabuls += countSyllables(words[i]);
			if(countSyllables(words[i]) > 2) {
				poly++;
			}
		}
		
		// print out results
		wordLength =  words.length;
		System.out.println("words: " + wordLength);
		System.out.println("Sentences: " + sentenceLength);
		System.out.println("Characters: " + charCount);
		System.out.println("Syllables: " + syllabuls);
		poly -= 1;
		System.out.println("Polysyllables: " + (poly));

		System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
		Scanner scan1 = new Scanner(System.in);
		String ans = scan1.nextLine();
		
		switch(ans) {
		case "ARI":
			ariCalc();
			averageCall++;
			System.out.println();
			break;
		case "FK":
			fkCalc();
			averageCall++;
			System.out.println();
			break;
		case "SMOG":
			smogCalc();
			averageCall++;
			System.out.println();
			break;
		case "CL":
			clCalc();
			averageCall++;
			System.out.println();
			break;
		case "all":
			System.out.println();
			ariCalc();
			System.out.println();
			fkCalc();
			System.out.println();
			smogCalc();
			System.out.println();
			clCalc();
			System.out.println();
			averageCall = 4;
			break;
			
		default:

			break;
		}
		System.out.println();
		System.out.println("This text should be understood in average by " + (double) averageScore/averageCall  + " years olds.");
	}

	
	public static void clCalc() {
	    String[] indexArray = {"6", "7", "9", "10", "11", "12", "13", "14", "15",
	            "16", "17", "18", "24", "24+"};
		double s = (double) sentenceLength / (double) wordLength * 100;
		double l = (double) charCount /(double) wordLength * 100;
		double score = 0.0588 * l - 0.296 * s - 15.8;
		System.out.printf("Coleman–Liau index: " + score +  " (about %s years olds).", indexArray[(int) Math.round(score) - 1 ]);
		if(indexArray[(int) Math.round(score) - 1 ].equals("24+")) {
			averageScore += 24;
		}else {
			averageScore += Integer.parseInt(indexArray[(int) Math.round(score) - 1 ]);
		}
	}


	public static void smogCalc() {
	    String[] indexArray = {"6", "7", "9", "10", "11", "12", "13", "14", "15",
	            "16", "17", "18", "24", "24+"};
		double score = 1.043 * Math.sqrt((double) poly * 30/(double) sentenceLength) + 3.1291;
		System.out.printf("Simple Measure of Gobbledygook: " + score +  " (about %s years olds).", indexArray[(int) Math.round(score) - 1]);
		if(indexArray[(int) Math.round(score) - 1 ].equals("24+")) {
			averageScore += 24;
		}else {
			averageScore += Integer.parseInt(indexArray[(int) Math.round(score) - 1 ]);
		}
	}


	public static void fkCalc() {
	    String[] indexArray = {"6", "7", "9", "10", "11", "12", "13", "14", "15",
	            "16", "17", "18", "24", "24+"};
		double score =  0.39 * wordLength/sentenceLength + 11.8 * syllabuls/wordLength - 15.59;
		System.out.printf("Flesch–Kincaid readability tests: " + score +  " (about %s years olds).", indexArray[(int) Math.round(score) - 1]);
		if(indexArray[(int) Math.round(score) - 1 ].equals("24+")) {
			averageScore += 24;
		}else {
			averageScore += Integer.parseInt(indexArray[(int) Math.round(score) - 1 ]);
		}
	}


	public static void ariCalc() {
	    String[] indexArray = {"6", "7", "9", "10", "11", "12", "13", "14", "15",
	            "16", "17", "18", "24", "24+"};
		// calculate score
		double score = 4.71 * charCount / wordLength + 0.5 * wordLength / sentenceLength - 21.43;
		System.out.printf("Automated Readability Index: " + score +  " (about %s years olds).", indexArray[(int) Math.round(score) - 1]);
		if(indexArray[(int) Math.round(score) - 1 ].equals("24+")) {
			averageScore += 24;
		}else {
			averageScore += Integer.parseInt(indexArray[(int) Math.round(score) - 1 ]);
		}
	}
	
	

	//calculate number of syllables
	public static int countSyllables(String word) {
		word = word.toLowerCase();
		int count = 0;
		word = word.toLowerCase();
		boolean isPrevVowel = false;
		for (int j = 0; j < word.length(); j++) {
			if (word.contains("a") || word.contains("e") || word.contains("i") || word.contains("o")
					|| word.contains("u")) {
				if (isVowel(word.charAt(j)) && !((word.charAt(j) == 'e') && (j == word.length() - 1))) {
					if (isPrevVowel == false) {
						count++;
						isPrevVowel = true;
					}
				} else {
					isPrevVowel = false;
				}
			} else {
				count++;
		
			}
		}
		return count;
	}

	public static boolean isVowel(char c) {
		if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
			return true;
		} else {
			return false;
		}
	}

}
