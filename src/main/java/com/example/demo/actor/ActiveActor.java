package com.example.demo.actor;

import javafx.scene.image.*;

/**
* Abstract base class for active game actors with image representation.
* Manages image loading, positioning, and basic movement capabilities.
*/
public abstract class ActiveActor extends ImageView {
	
	/** Base directory for actor image resources. */
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs an active actor with specified image and initial position.
	 * 
	 * @param imageName Name of the actor's image file
	 * @param imageHeight Desired height of the image
	 * @param initialXPos Initial horizontal position
	 * @param initialYPos Initial vertical position
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		//this.setImage(new Image(IMAGE_LOCATION + imageName));
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/** 
	 * Updates the actor's position.
	 * Must be implemented by subclasses. 
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by specified distance.
	 * 
	 * @param horizontalMove Horizontal translation distance
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by specified distance.
	 * 
	 * @param verticalMove Vertical translation distance
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}
