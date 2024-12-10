package com.example.demo.levels;

import com.example.demo.image.ShieldImage;

import javafx.scene.Group;

/**
 * Extends LevelView to provide specialized UI elements for Level Two.
 * Adds a shield image to the standard level view components.
 * 
 */
public class LevelViewLevelBoss extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;
	
    /**
     * Constructs LevelViewLevelTwo with a root group and initial heart display.
     * Initializes the parent LevelView and adds a shield image to the scene.
     * 
     * @param root The root JavaFX Group to which UI elements will be added
     * @param heartsToDisplay Initial number of hearts to display for the player
     */
	public LevelViewLevelBoss(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}
	
    /**
     * Adds additional images specific to Level Two to the root group.
     * In this case, adds the shield image to the scene.
     */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}
	
    /**
     * Makes the shield image visible on the game screen.
     * Used to display the shield during specific game events or states.
     */
	public void showShield() {
		shieldImage.showShield();
	}

    /**
     * Hides the shield image from the game screen.
     * Used to remove the shield during specific game events or states.
     */
	public void hideShield() {
		shieldImage.hideShield();
	}

}
