package com.example.demo.projectile;

/**
 * Represents a projectile fired by the user's plane in the game.
 * This projectile moves horizontally to the right with a consistent velocity.
 */
public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;

    /**
     * Constructs a UserProjectile at a specific horizontal and vertical position.
     * 
     * @param initialXPos The initial horizontal position where the projectile is created
     * @param initialYPos The initial vertical position where the projectile is created
     */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

    /**
     * Updates the position of the projectile by moving horizontally.
     * The projectile moves at a constant velocity defined by HORIZONTAL_VELOCITY.
     */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	
    /**
     * Updates the actor's state by updating its position.
     * This method is called each frame to maintain the projectile's movement.
     */
	@Override
	public void updateActor() {
		updatePosition();
	}
	
}
