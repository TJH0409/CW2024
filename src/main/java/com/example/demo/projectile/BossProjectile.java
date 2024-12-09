package com.example.demo.projectile;

/**
 * Represents a special projectile fired by a boss enemy in the game.
 * This projectile moves horizontally with a specific velocity and uses a fireball image.
 */
public class BossProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "fireball.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;

    /**
     * Constructs a BossProjectile at a specific vertical position.
     * 
     * @param initialYPos The initial vertical position where the projectile is created
     */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
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
