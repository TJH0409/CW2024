package com.example.demo.levels;

import com.example.demo.image.GameOverImage;
import com.example.demo.image.HeartDisplay;
import com.example.demo.image.WinImage;

import javafx.scene.Group;

/**
 * Manages the visual elements of a game level, including heart display, win, and game over screens.
 * Responsible for updating and displaying user interface elements during gameplay.
 * 
 * @author Developer
 * @version 1.0
 */
public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = -160;
	private static final int LOSS_SCREEN_Y_POSISITION = -375;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	
    /**
     * Constructs a LevelView with specified root group and initial number of hearts.
     * Initializes heart display, win, and game over images with predefined positions.
     * 
     * @param root The root JavaFX Group to which UI elements will be added
     * @param heartsToDisplay Initial number of hearts to display for the player
     */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSISITION);
	}
	
    /**
     * Adds the heart display to the game root.
     * Makes the player's health visually represented on the screen.
     */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}
	
    /**
     * Displays the win image on the game screen.
     * Triggered when the player successfully completes the level.
     */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}
	
    /**
     * Displays the game over image on the screen.
     * Shown when the player fails to complete the level.
     */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}
	
    /**
     * Updates the heart display to reflect the player's current health.
     * Removes hearts from the display based on the number of hearts remaining.
     * 
     * @param heartsRemaining The current number of hearts the player has
     */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

}
