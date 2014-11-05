/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;
import acm.program.*;
import acm.graphics.*;



public class HangmanCanvasExtended extends GCanvas {

	/* Method: reset */
	/** Resets the display so that only the scaffold appears */
	public void reset() {
		this.removeAll();
		
		//Creates Mario compound object for when he falls
		allOfMario = new GCompound();
		add(allOfMario, MARIO_X, MARIO_Y);
		
		//Add Super Mario background
		GImage background = new GImage("hangmanExtensionbckg.png");
		background.setSize(getWidth(), getHeight());
		add(background);
		background.sendToBack();
		
		//Add "front background" (for when Mario falls at the end of the game)
		frontBckg = new GImage("frontBackground.png");
		add(frontBckg, 0, getHeight() - frontBckg.getHeight());
		background.sendToFront();
		
		//The word that needs to be guessed will be added to the canvas from the very beginning,
		//and it will be updated with each method call to "displayWord".
		wordDisplayed = new GLabel("");
		wordDisplayed.setFont("Helvetica-bold-28");
		wordDisplayed.setColor(Color.WHITE);
		add(wordDisplayed, WORD_X_OFFSET, getHeight() - WORD_Y_OFFSET);
		
		//Same with the label of the incorrect characters.
		incorrectChars = new GLabel("");
		incorrectChars.setFont("Helvetica-22");
		incorrectChars.setColor(Color.WHITE);
		add(incorrectChars, INCORRECT_CHARS_X_OFFSET, getHeight() - INCORRECT_CHARS_Y_OFFSET);
	}

	/* Method: displayWord */
	/** Updates the word on the screen to correspond to the current
	 * state of the game.  The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		wordDisplayed.setLabel(word);
	}

	/* Method: noteIncorrectGuess */
	/** Updates the display to correspond to an incorrect guess by the
	 * user.  Calling this method causes the next body part to appear
	 * on the scaffold and adds the letter to the list of incorrect
	 * guesses that appears at the bottom of the window.
 	 */
	public void noteIncorrectGuess(char letter) {
		String chars = incorrectChars.getLabel();
		incorrectChars.setLabel(chars + letter);
		int incorrectGuesses = incorrectChars.getLabel().length();
		
		switch (incorrectGuesses) {
			case 1: drawArm(-1); break;
			case 2:drawHead(); break;
			case 3: drawBody(); break;
			case 4: drawArm(1); break;
			case 5: drawLeg(-1); break;
			case 6: drawLeg(1); break;
			case 7: drawFoot(-1); break;
			case 8: drawFoot(1);
					moveMario();
		}
	}
	
	private void moveMario() {
		while (allOfMario.getY() < frontBckg.getY()) {
			allOfMario.move(0,3);
			control.pause(10);
		}
	}
	
	/* Method: drawHead */
	/** Draws the head of the Mario */
	private void drawHead() {
		head = new GImage("Head.png");
		add(head, MARIO_X, MARIO_Y);
		allOfMario.add(head);
	}
	
	/* Method: drawBody */
	/** Draws the body of the Mario */
	private void drawBody() {		
		body = new GImage("Body.png");
		add(body, MARIO_X, MARIO_Y);
		allOfMario.add(body);
	}
	
	/* Method: drawArm */
	/** Draws either the left or right arm of  Mario.
	 * @param direction Determines which arm is going to be drawn - left or right. 
	 */
	private void drawArm(int direction) {
		if (direction == -1) {
			rightArm = new GImage("Right Arm.png");
			add(rightArm, MARIO_X, MARIO_Y);
			allOfMario.add(rightArm);
		}
		else {
			leftArm = new GImage("Left Arm.png");
			add(leftArm, MARIO_X, MARIO_Y);
			allOfMario.add(leftArm);
		}
	}
	
	/* Method: drawLeg */
	/** Draws either the left or right leg of  Mario.
	 * @param direction Determines which leg is going to be drawn - left or right.
	 */
	private void drawLeg(int direction) {
		if (direction == -1) {
			rightLeg = new GImage("Right Leg.png");
			add(rightLeg, MARIO_X, MARIO_Y);
			allOfMario.add(rightLeg);
		}
		else {
			leftLeg = new GImage("Left Leg.png");
			add(leftLeg, MARIO_X, MARIO_Y);
			allOfMario.add(leftLeg);
		}
	}
	
	/* Method: drawFoot */
	/** Draws either the right or the left foot of the hangman.
	 * @param direction Determines which foot is to be drawn - left or right.
	 */
	private void drawFoot(int direction) {
		if (direction == -1) {
			rightFoot = new GImage("Right Foot.png");
			add(rightFoot, MARIO_X, MARIO_Y);
			allOfMario.add(rightFoot);
		}
		else {
			leftFoot = new GImage("Left Foot.png");
			add(leftFoot, MARIO_X, MARIO_Y);
			allOfMario.add(leftFoot);
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	
	/** Distance from left edge of canvas to the word and incorrect character labels */
	private static final int WORD_X_OFFSET = 25;
	
	/** Distance from bottom of canvas to the y-coordinate of the word label */
	private static final int WORD_Y_OFFSET = 15;
	
	/**Distance from the left edge of canvas to the x-coordinate of the incorrect character label */
	private static final int INCORRECT_CHARS_X_OFFSET = 270;
	
	/**Distance from the bottom of canvas to the y-coordinate of the incorrect character label */
	private static final int INCORRECT_CHARS_Y_OFFSET = 20;
	
	/** Label displaying the secret word */
	private GLabel wordDisplayed;
	
	/** Label displaying the characters that have been incorrectly guessed */
	private GLabel incorrectChars;
	
	/** This double notes the y-coordinate of the scaffold. It was made an instance variable
	 * to use it as a reference point when creating hangman's body parts.*/
	private double scaffoldY;
	
	/** The x coordinate of all of Mario's body parts. */
	private static final double MARIO_X = 150.0;
	
	/** The y coordinate of all of Mario's body parts. */
	private static final double MARIO_Y = 80.0;
	
	private GImage head;
	
	private GImage body;
	
	private GImage rightArm;
	
	private GImage leftArm;
	
	private GImage leftLeg;
	
	private GImage rightLeg;
	
	private GImage rightFoot;
	
	private GImage leftFoot;
	
	private GCompound allOfMario;
	
	private GImage frontBckg;
	
	GraphicsProgram control;
}
