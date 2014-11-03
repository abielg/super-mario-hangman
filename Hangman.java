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
			
		}
		
		
	}
    
    private String getNextWord(HangmanLexicon lexicon) {
    	int index = rgen.nextInt(0, lexicon.getWordCount() - 1);
    	return lexicon.getWord(index);
    }
    
    private String createDisplayOfWord(String chosenWord) {
    	String result = "";
    	for (int i = 0; i < chosenWord.length(); i++ ){
    		result += "-";
    	}
    	return result;
    }
    
    RandomGenerator rgen =  RandomGenerator.getInstance();
    private int NUM_GUESSES;

}
