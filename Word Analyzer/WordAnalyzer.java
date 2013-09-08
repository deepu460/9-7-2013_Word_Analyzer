package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class analyzes a text file and prints the number of words, the number of
 * letters, the number of symbols, & the top 3 most commonly used words and
 * letters.
 */
public class WordAnalyzer {
	/**
	 * The main method. Prints the statistics of the text file
	 * 
	 * @param args
	 *            - Unused
	 */
	public static void main(String[] args) {
		// Wikipedia's lorem-ipsum.
		File file = new File("res/lorem ipsum.txt");
		Scanner scanner = null;
		String[] mostCommen = null;
		int temp = 0;

		scanner = resetScanner(scanner, file);

		if (!(scanner == null)) {
			// The number of words
			temp = numOfWords(scanner);
			System.out.println("Number of words: ".concat(Integer
					.toString(temp)));

			// The number of letters
			scanner = resetScanner(scanner, file);
			temp = numOfLet(scanner);
			System.out.println("Number of letters: ".concat(Integer
					.toString(temp)));

			// The number of symbols
			scanner = resetScanner(scanner, file);
			temp = numOfSymbols(scanner);
			System.out.println("Number of symbols: ".concat(Integer
					.toString(temp)));

			// The most comment words
			scanner = resetScanner(scanner, file);
			mostCommen = mostCommenWords(scanner);
			System.out.print("Most commen words:");

			for (int ix = 0; ix < mostCommen.length; ix++) {
				System.out.print(" ".concat(mostCommen[ix]));
			}
			System.out.print("\n");

			// The most commen letters
			scanner = resetScanner(scanner, file);
			mostCommen = mostCommenLet(scanner);
			System.out.print("Most commen letters:");
			for (int ix = 0; ix < mostCommen.length; ix++) {
				System.out.print(" ".concat(mostCommen[ix]));
			}
			System.out.print("\n");

		}
		// Closes the scanner...
		scanner.close();
	}

	/**
	 * This gets the number of words in a doc, if you can supply a scanner
	 * that is pointed at the text document.
	 * 
	 * @param s
	 *            - The scanner
	 * @return The # of words.
	 */
	private static int numOfWords(Scanner s) {
		int words = 0;

		while (s.hasNext()) {
			words += s.nextLine().split(" ").length;
		}

		return words;
	}

	/**
	 * This gets the number of letters, if supply a scanner pointed at the
	 * text document.
	 * 
	 * @param s
	 *            - The scanner
	 * @return The # of letters
	 */
	private static int numOfLet(Scanner s) {
		char[] charLine;
		String line;
		int letters = 0;

		while (s.hasNext()) {
			line = s.nextLine().replaceAll(" ", "");
			charLine = line.toCharArray();

			for (char c : charLine) {
				letters += (c < 91 && c > 64 || c < 123 && c > 96) ? 1 : 0;
			}

		}

		return letters;
	}
	/**
	 * This gets the number of symbols, if supply a scanner pointed at the
	 * text document.
	 * 
	 * @param s
	 *            - The scanner
	 * @return The # of symbols
	 */
	private static int numOfSymbols(Scanner s) {
		char[] charLine;
		String line;
		int symbols = 0;

		while (s.hasNext()) {
			line = s.nextLine().replaceAll(" ", "");
			charLine = line.toCharArray();

			for (char c : charLine) {
				symbols += (!(c < 91 && c > 64) || !(c < 123 && c > 96)) ? 1
						: 0;
			}

		}

		return symbols;
	}
	/**
	 * This gets the 3 most common words.
	 * @param s - The scanner
	 * @return A string array of the 3 most common words
	 */
	private static String[] mostCommenWords(Scanner s) {
		ArrayList<String> common = new ArrayList<>();
		String temp;
		String[] line;
		String[] topThree = new String[3];
		int[] topThreeAmount = { 0, 0, 0 };
		int instances = 0;

		while (s.hasNext()) {
			line = s.nextLine().split(" ");

			for (String string : line) {

				if (string.length() > 1)
					common.add(string);
			}

		}

		Collections.sort(common);
		temp = common.get(0);

		for (int ix = 0; ix < common.size(); ix++) {
			if (temp.equalsIgnoreCase(common.get(ix))) {
				instances++;
			} else {
				if (instances > topThreeAmount[0]) {
					topThree[0] = temp;
					topThreeAmount[0] = instances;
					instances = 0;
				} else if (instances > topThreeAmount[1]) {
					topThree[1] = temp;
					topThreeAmount[1] = instances;
					instances = 0;
				} else if (instances > topThreeAmount[2]) {
					topThree[2] = temp;
					topThreeAmount[2] = instances;
					instances = 0;
				} else {
					instances = 0;
				}

				temp = common.get(ix);
			}
		}

		return topThree;
	}
	
	/**
	 * This finds the most common letters
	 * @param s - The scanner
	 * @return A string array of the 3 most common letters
	 */
	private static String[] mostCommenLet(Scanner s) {
		ArrayList<String> common = new ArrayList<>();
		String temp1;
		String[] line;
		String[] topThree = new String[3];
		int[] topThreeAmount = { 0, 0, 0 };
		int instances = 0;

		while (s.hasNext()) {
			line = s.nextLine().split(" ");

			for (String string : line) {
				for (char c : string.toCharArray()) {
					if (c < 91 && c > 64 || c < 123 && c > 96) {
						common.add(String.valueOf(c));
					}
				}
			}

		}

		Collections.sort(common);
		temp1 = common.get(0);

		for (String string : common) {
			if (temp1.equalsIgnoreCase(string)) {
				instances++;
			} else {
				if (instances > topThreeAmount[0]) {
					topThree[0] = temp1;
					topThreeAmount[0] = instances;
					instances = 0;
				} else if (instances > topThreeAmount[1]) {
					topThree[1] = temp1;
					topThreeAmount[1] = instances;
					instances = 0;
				} else if (instances > topThreeAmount[2]) {
					topThree[2] = temp1;
					topThreeAmount[2] = instances;
					instances = 0;
				} else {
					instances = 0;
				}

				temp1 = string;
			}
		}

		return topThree;
	}
	/**
	 * This resets the scanner to the begining of the file.
	 * @param scanner - The scanner
	 * @param file - The file
	 * @return The reset scanner
	 */
	private static Scanner resetScanner(Scanner scanner, File file) {
		try {
			return scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the file. Quitting...");
			System.exit(-1);
		}
		// Rather unnecessary code, but it won't compile without it.
		return null;
	}

}