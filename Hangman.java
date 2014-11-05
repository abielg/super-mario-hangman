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
	
	/* Methd: run */
	/** Runs the Hangman game */
    public void run() {
    	//Creating the lexicon and choosing a random word.
		HangmanLexicon listOfWords = new HangmanLexicon();
		String word = listOfWords.getWord();
		
		println("Welcome to Hangman!");
		displayOfWord = createDisplayOfWord(word);
		setUpCanvas();
		canvas.displayWord(displayOfWord);
		
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
				if (word.indexOf(guessChar) != -1)
					characterIsCorrect(guessChar, word);
				else {
					characterIsIncorrect(guessChar, word);
				}
			}
			canvas.displayWord(displayOfWord);
		}
	}
    
    public void init() {
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
    
    private void setUpCanvas() {
    	canvas.reset();
    }
    
    /* Method: characterIsCorrect */
    /** Notifies the user that he correctly guessed a character in the secret
     * word. Modifies the displayed word by changing the respective dash(es)
     * into the character the user just guessed. If this was the last character
     * needed to complete the word, the user is notified, and NUM_GUESSES is
     * changed to 0 to break the while loop in the run method.
     * @param guessedChar The character that the user correctly guessed.
     * @param secretWord The secret word (String) that the user is trying to guess.
     * @param displayedWord The current status of the secret word, represented by
     * 		  dashes in any characters that the user hasn't guessed.
     */
    private void characterIsCorrect(char guessedChar, String secretWord) {
    	println("That guess is correct.");
    	for (int i = 0; i < secretWord.length(); i++) {
    		if (guessedChar == secretWord.charAt(i)) {
    			if (secretWord.length() == 1)
    				displayOfWord = "" + guessedChar;
    			else if (i==0)
    				displayOfWord = guessedChar + displayOfWord.substring(i+1);
    			else if (i == secretWord.length() - 1)
    				displayOfWord = displayOfWord.substring(0,i) + guessedChar;
    			else
    				displayOfWord = displayOfWord.substring(0,i) + guessedChar + 
    				displayOfWord.substring(i+1);
    		}
    	}
    	
    	if (displayOfWord.equals(secretWord)) {
    		println("You guessed the word: " + secretWord);
    		println("You win.");
    		NUM_GUESSES = 0;
    	}	
    }
    
    /* Method: characterIsIncorrect */
    /** Notifies the user that his guess was incorrect. If NUM_GUESSES = 0,
     * the user gets notified that the game is over. It also calls the
     * noteIncorrectGuesses method to display the hangman's new bodypart and
     * the character that has been incorrectly guessed.
     * @param guessedChar The character that the user guessed.
     * @param secretWord The secret word that the user has to guess.
     */
    private void characterIsIncorrect(char guessedChar, String secretWord) {
    	println("There are no " + guessedChar + "'s in the word.");
    	NUM_GUESSES --;
    	canvas.noteIncorrectGuess(guessedChar);
    	if (NUM_GUESSES == 0) {
    		println("You're completely hung.");
    		println("The word was: " + secretWord);
    		println("You lose.");
    	}
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
       
    /** Number of guesses remaining */
    private int NUM_GUESSES = 8;
    
    /** String displayed to the user, with dashes for unsolved characters*/
    private String displayOfWord;
    
    private HangmanCanvas canvas;

}
