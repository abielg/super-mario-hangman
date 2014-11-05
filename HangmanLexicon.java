/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;

import java.io.*;
import java.util.*;

public class HangmanLexicon {
	
	public HangmanLexicon() {
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			wordList = new ArrayList<String>();
			while (true) {
				String line = rd.readLine();
				if (line == null)
					break;
				wordList.add(line);
			}
			rd.close();
		} catch (IOException ex) {
				throw new ErrorException(ex);
			}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord() {
		int index = rgen.nextInt(0,getWordCount());
		return wordList.get(index);
	}
	
	/**ArrayList with list of words */
	private ArrayList<String> wordList;
	
    /** Random number generator instance variable */
    RandomGenerator rgen =  RandomGenerator.getInstance();
}
