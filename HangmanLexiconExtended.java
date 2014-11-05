/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file imports a list of words and chooses one of them
 * to be the random word that the user has to guess.
 */

import acm.util.*;

import java.io.*;
import java.util.*;

public class HangmanLexiconExtended {
	/* Method: class constructor */
	/** Creates a BufferedReader to read all words from the lexicon list, and 
	 * adds them to an array list.
	 */
	public HangmanLexiconExtended() {
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
	
	/* Method: getWordCount */
	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}
	
	/* Method: getWord */
	/** Returns the word at the specified index of the array list. */
	public String getWord() {
		int index = rgen.nextInt(0,getWordCount());
		return wordList.get(index);
	}
	
	/**ArrayList with list of words */
	private ArrayList<String> wordList;
	
    /** Random number generator instance variable */
    RandomGenerator rgen =  RandomGenerator.getInstance();
}
