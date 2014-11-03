/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;

public class Hangman extends ConsoleProgram {

    public void run() {
    	//Creating the lexicon and choosing a random word.
		HangmanLexicon listOfWords = new HangmanLexicon();
		String word = getNextWord(listOfWords);
		
		println("Welcome to Hangman!");
		NUM_GUESSES = 8;
		String displayOfWord = createDisplayOfWord(word);
		
		while(NUM_GUESSES > 0) {
			println("The word now looks like this: " + displayOfWord);
			println("You have " + NUM_GUESSES + " guesses left.");
			String guessString = readLine("Your guess: ");
			if (! guessIsAcceptable(guessString)) {
				println("You must enter a single letter character.");
			}
			else {
				char guessChar = guessString.charAt(0);
				guessChar = Character.toUpperCase(guessChar);
			}
			
		}
		
		
	}
    
    /* Method: getNextWord */
    /** Returns a random String from the list of word found in the lexicon.
     * @param lexicon The list of String words
     */
    private String getNextWord(HangmanLexicon lexicon) {
    	int index = rgen.nextInt(0, lexicon.getWordCount() - 1);
    	return lexicon.getWord(index);
    }
    
    /* Method: createDisplayOfWord */
    /** Creates the visual representation of the secret word, returning
     * a string with an amount of dashes equal to the number of characters
     * in the word.
     * @param chosenWord The random secret word retrieved from the lexicon
     */
    private String createDisplayOfWord(String chosenWord) {
    	String result = "";
    	for (int i = 0; i < chosenWord.length(); i++ ){
    		result += "-";
    	}
    	return result;
    }
    
    /* Method: guessIsAcceptable */
    /** Checks whether the String inputed by the user is one character in
     * length and if it is a letter. Returns a boolean specifying this.
     * @param guessString The String that the user inputed.
     */
    private boolean guessIsAcceptable(String guessString) {
    	if (guessString.length() != 1)
    		return false;
    	if (Character.isLetter(guessString.charAt(0)))
    		return true;
    	else
    		return false;
    }
    
    RandomGenerator rgen =  RandomGenerator.getInstance();
    private int NUM_GUESSES;

}
