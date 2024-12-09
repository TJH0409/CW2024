package com.example.demo.projectile;

import com.example.demo.actor.ActiveActorDestructible;

/**
* Abstract base class for projectiles in the game.
* Provides common functionality for destructible projectile actors.
*/
public abstract class Projectile extends ActiveActorDestructible {

	   /**
	    * Constructs a new Projectile with specified image and initial position.
	    * 
	    * @param imageName Name of the projectile's image asset
	    * @param imageHeight Height of the projectile image
	    * @param initialXPos Initial horizontal position
	    * @param initialYPos Initial vertical position
	    */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	   /**
	    * Immediately destroys the projectile when damage is taken.
	    * Projectiles are removed from the game upon any damage.
	    */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	   /**
	    * Abstract method to update projectile's position.
	    * Must be implemented by specific projectile types.
	    */
	@Override
	public abstract void updatePosition();

}
