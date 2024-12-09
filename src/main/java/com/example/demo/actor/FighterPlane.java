package com.example.demo.actor;

/**
* Abstract base class representing a fighter plane in the game.
* Manages health, damage, and basic projectile firing mechanics.
*/
public abstract class FighterPlane extends ActiveActorDestructible {

	private int health;

	   /**
	    * Constructs a fighter plane with specified image and initial health.
	    * 
	    * @param imageName Name of the plane's image asset
	    * @param imageHeight Height of the plane image
	    * @param initialXPos Initial horizontal position
	    * @param initialYPos Initial vertical position
	    * @param health Starting health points
	    */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	   /**
	    * Abstract method to fire a projectile from the fighter plane.
	    * 
	    * @return A new projectile or null
	    */
	public abstract ActiveActorDestructible fireProjectile();
	
	   /**
	    * Reduces health when damage is taken.
	    * Destroys the plane if health reaches zero.
	    */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	   /**
	    * Calculates the X-coordinate for projectile spawn.
	    * 
	    * @param xPositionOffset Horizontal offset from plane's position
	    * @return Calculated X-coordinate for projectile
	    */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	   /**
	    * Calculates the Y-coordinate for projectile spawn.
	    * 
	    * @param yPositionOffset Vertical offset from plane's position
	    * @return Calculated Y-coordinate for projectile
	    */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	   /**
	    * Checks if the plane's health has been depleted.
	    * 
	    * @return true if health is zero, false otherwise
	    */
	private boolean healthAtZero() {
		return health == 0;
	}

	   /**
	    * Retrieves the current health of the fighter plane.
	    * 
	    * @return Current health points
	    */
	public int getHealth() {
		return health;
	}
		
}
