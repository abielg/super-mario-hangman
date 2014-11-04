/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		this.removeAll();
		double scaffoldX = (getWidth() / 2) - BEAM_LENGTH;
		double scaffoldY = ((getHeight() - SCAFFOLD_HEIGHT) / 2) - SCAFFOLD_VERT_OFFSET;
		//scaffold
		add(new GLine(scaffoldX, scaffoldY, scaffoldX, scaffoldY + SCAFFOLD_HEIGHT));
		//beam
		add(new GLine(scaffoldX, scaffoldY, (scaffoldX + BEAM_LENGTH), scaffoldY));
		//rope
		add(new GLine((scaffoldX + BEAM_LENGTH), scaffoldY,(scaffoldX + BEAM_LENGTH), scaffoldY + ROPE_LENGTH));
		
		//The word that needs to be guessed will be added to the canvas from the very beginning,
		//and it will be updated with each method call to "displayWord".
		wordDisplayed = new GLabel("");
		wordDisplayed.setFont("Helvetica-24");
		add(wordDisplayed, LABEL_X_OFFSET, WORD_Y_OFFSET);
		
		//Same with the label of the incorrect characters.
		incorrectChars = new GLabel("");
		incorrectChars.setFont("Helvetica-15");
		add(incorrectChars, LABEL_X_OFFSET, INCORRECT_CHARS_Y_OFFSET);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		wordDisplayed.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		String chars = incorrectChars.getLabel();
		incorrectChars.setLabel(chars + letter);
		int incorrectGuesses = incorrectChars.getLabel().length();
		
		switch (incorrectGuesses) {
			case 1: drawHead(); break;
			case 2:drawBody(); break;
			case 3: drawArm(-1); break;
			case 4: drawArm(1); break;
			case 5: drawLeg(-1); break;
			case 6: drawLeg(1); break;
			case 7: drawFoot(-1); break;
			case 8: drawFoot(1);
		}
	}
	
	private void drawhead() {
		double x = (getWidth() / 2) - HEAD_RADIUS;
		double y = 
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int SCAFFOLD_VERT_OFFSET = 0;
	
	/** Distance from left edge of canvas to the word and incorrect character labels */
	private static final int LABEL_X_OFFSET = 25;
	
	/** Distance from bottom of canvas to the y-coordinate of the word label */
	private static final int WORD_Y_OFFSET = 60;
	
	/**Distance from the bottom of canvas to the y-coordinate of the incorrect character label */
	private static final int INCORRECT_CHARS_Y_OFFSET = 20;
	
	/** Label displaying the secret word */
	private GLabel wordDisplayed;
	
	/** Label displaying the characters that have been incorrectly guessed */
	private GLabel incorrectChars;
}
